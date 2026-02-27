import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  stages: [
    { duration: "10s", target: 20 },
    { duration: "20s", target: 80 },
    { duration: "20s", target: 160 },
    { duration: "10s", target: 0 },
  ],
  thresholds: {
    http_req_failed: ["rate<0.02"],
    http_req_duration: ["p(95)<1500"],
  },
};

export function setup() {
  const res = http.get("http://lb:9999/events");
  const ok = check(res, { "events status is 200": (r) => r.status === 200 });
  if (!ok) {
    return { eventIds: [] };
  }
  let events = [];
  try {
    events = res.json();
  } catch (e) {
    return { eventIds: [] };
  }
  const ids = Array.isArray(events) ? events.map((e) => e.id).filter(Boolean) : [];
  return { eventIds: ids };
}

function randomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

export default function (data) {
  const eventIds = data.eventIds;
  if (!eventIds || eventIds.length === 0) {
    sleep(0.2);
    return;
  }

  const eventId = eventIds[randomInt(0, eventIds.length - 1)];
  const quantity = randomInt(1, 4);
  const payload = JSON.stringify({ eventId, quantity });

  const res = http.post("http://lb:9999/orders", payload, {
    headers: { "Content-Type": "application/json" },
  });

  check(res, { "order created": (r) => r.status === 201 });
  sleep(0.05);
}

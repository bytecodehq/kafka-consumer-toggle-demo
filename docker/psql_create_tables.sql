CREATE TABLE kafka_consumer_state(
    id TEXT NOT NULL PRIMARY KEY,
    state TEXT NOT NULL DEFAULT 'START'
);

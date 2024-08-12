CREATE TABLE IF NOT EXISTS scheduling_periods (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL
);

CREATE TABLE IF NOT EXISTS calculation_results (
    id BIGSERIAL PRIMARY KEY,
    scheduling_period_id BIGINT NOT NULL,
    timestamp TIMESTAMPTZ NOT NULL,
    result DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (scheduling_period_id) REFERENCES scheduling_periods(id)
);
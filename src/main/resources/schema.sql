DROP TABLE IF EXISTS harbor_history;
DROP TABLE IF EXISTS infractions;
DROP TABLE IF EXISTS ssa_assessments;
DROP TABLE IF EXISTS ship_info;

CREATE TABLE ship_info (
    file_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ship_name VARCHAR(100) NOT NULL,
    ship_flag VARCHAR(30),
    ship_type VARCHAR(50),
    port_of_registry VARCHAR(70),
    working_languages VARCHAR(100),
    official_registry_number BIGINT UNIQUE,
    call_sign VARCHAR(20),
    ship_owner VARCHAR(100),
    class VARCHAR(100),
    cargo_capacity_tonnage FLOAT,
    date_of_ssa DATE,
    ssa_by VARCHAR(100),
    is_high_risk_crew BOOLEAN DEFAULT FALSE,
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ship_status VARCHAR(30)
);

CREATE TABLE ssa_assessments (
    ssa_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    file_id BIGINT NOT NULL,
    ssa_type VARCHAR(20),
    ssa_score INT CHECK (ssa_score BETWEEN 0 AND 100),
    ssa_comments VARCHAR(255),
    is_suspect BOOLEAN DEFAULT FALSE,
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (file_id) REFERENCES ship_info(file_id) ON DELETE CASCADE
);

CREATE TABLE infractions (
    infraction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    file_id BIGINT NOT NULL,
    infraction_type VARCHAR(20) CHECK (infraction_type IN ('documentation_issue', 'physical_dmg', 'custom')),
    details TEXT,
    infraction_date DATE,
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (file_id) REFERENCES ship_info(file_id) ON DELETE CASCADE
);

CREATE TABLE harbor_history (
    history_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    file_id BIGINT NOT NULL,
    port_visited VARCHAR(100) NOT NULL,
    country VARCHAR(50),
    arrival_date DATE NOT NULL,
    departure_date DATE DEFAULT NULL,
    is_high_risk BOOLEAN DEFAULT FALSE,
    risk_reason VARCHAR(20) CHECK (risk_reason IN ('disease', 'drugs', 'terrorism', 'smuggling', 'other')),
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (file_id) REFERENCES ship_info(file_id) ON DELETE CASCADE
);
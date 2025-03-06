-- Sample data for ship_info table
INSERT INTO ship_info (ship_name, ship_flag, ship_type, port_of_registry, working_languages, official_registry_number, call_sign, ship_owner, class, cargo_capacity_tonnage, date_of_ssa, ssa_by, is_high_risk_crew, ship_status)
VALUES
('Ocean Explorer', 'Panama', 'Container Ship', 'Panama City', 'English, Spanish', 12345678, 'WXYZ123', 'Global Shipping Co.', 'A1', 52000.5, '2023-05-15', 'Maritime Safety Authority', FALSE, 'ACTIVE'),
('Northern Star', 'Norway', 'Bulk Carrier', 'Oslo', 'Norwegian, English', 87654321, 'ABCD456', 'Nordic Freight Ltd.', 'A2', 75000.0, '2023-06-20', 'Nordic Maritime Agency', FALSE, 'ACTIVE'),
('Pacific Voyager', 'Marshall Islands', 'Oil Tanker', 'Majuro', 'English', 55566677, 'EFGH789', 'Pacific Oil Transport Inc.', 'A1+', 120000.8, '2023-04-10', 'International Maritime Organization', TRUE, 'UNDER_REVIEW'),
('Mediterranean Queen', 'Greece', 'Cruise Ship', 'Athens', 'Greek, English, French', 33344455, 'IJKL012', 'Mediterranean Cruises SA', 'A1', 0.0, '2023-07-05', 'European Maritime Safety Agency', FALSE, 'ACTIVE'),
('Baltic Trader', 'Estonia', 'General Cargo', 'Tallinn', 'Estonian, Russian, English', 99900011, 'MNOP345', 'Baltic Trading Company', 'B1', 28500.2, '2022-12-12', 'Estonian Maritime Administration', FALSE, 'MAINTENANCE');

-- Sample data for ssa_assessments table
INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES
(1, 'violence', 15, 'Low risk assessment, crew screening complete', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES
(1, 'health', 25, 'Minor concerns with sanitation standards', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES
(2, 'cyber', 45, 'Outdated navigation systems, recommended upgrades', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES
(3, 'illegal', 75, 'Previous suspicious cargo findings, requires thorough inspection', TRUE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES
(3, 'health', 60, 'Reported illness among crew members, quarantine protocol advised', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES
(4, 'other', 30, 'Passenger capacity concerns during peak season', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES
(5, 'physical_dmg', 50, 'Hull integrity issues reported in previous inspection', FALSE);

-- Sample data for infractions table
INSERT INTO infractions (file_id, infraction_type, details, infraction_date)
VALUES
(1, 'documentation_issue', 'Missing updated crew manifest', '2023-08-10'),
(3, 'physical_dmg', 'Damaged ballast water management system', '2023-07-22'),
(3, 'documentation_issue', 'Expired safety certificates', '2023-06-15'),
(4, 'custom', 'Insufficient life saving equipment for passenger capacity', '2023-09-05'),
(5, 'physical_dmg', 'Corrosion on port side hull plating', '2023-04-18');

-- Sample data for harbor_history table
INSERT INTO harbor_history (file_id, port_visited, country, arrival_date, departure_date, is_high_risk, risk_reason)
VALUES
(1, 'Singapore Harbor', 'Singapore', '2023-07-01', '2023-07-05', FALSE, NULL),
(1, 'Port of Shanghai', 'China', '2023-07-12', '2023-07-15', FALSE, NULL),
(2, 'Rotterdam Port', 'Netherlands', '2023-06-10', '2023-06-14', FALSE, NULL),
(3, 'Port of Lagos', 'Nigeria', '2023-05-20', '2023-05-25', TRUE, 'smuggling'),
(3, 'Mombasa Port', 'Kenya', '2023-06-02', '2023-06-07', TRUE, 'drugs'),
(4, 'Port of Barcelona', 'Spain', '2023-08-15', '2023-08-18', FALSE, NULL),
(4, 'Port of Marseille', 'France', '2023-08-20', '2023-08-23', FALSE, NULL),
(5, 'Gdansk Port', 'Poland', '2023-03-10', '2023-03-15', FALSE, NULL),
(5, 'Stockholm Port', 'Sweden', '2023-03-18', '2023-03-22', FALSE, NULL);
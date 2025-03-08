-- Sample data for ship_info table
INSERT INTO ship_info (ship_name, ship_flag, ship_type, port_of_registry, working_languages, official_registry_number, call_sign, ship_owner, class, cargo_capacity_tonnage, date_of_ssa, ssa_by, is_high_risk_crew, ship_status)
VALUES
('Ocean Explorer', 'Panama', 'Container Ship', 'Panama City', 'English, Spanish', 12345678, 'WXYZ123', 'Global Shipping Co.', 'A1', 52000.5, '2023-05-15', 'Maritime Safety Authority', FALSE, 'ACTIVE'),
('Northern Star', 'Norway', 'Bulk Carrier', 'Oslo', 'Norwegian, English', 87654321, 'ABCD456', 'Nordic Freight Ltd.', 'A2', 75000.0, '2023-06-20', 'Nordic Maritime Agency', FALSE, 'ACTIVE'),
('Pacific Voyager', 'Marshall Islands', 'Oil Tanker', 'Majuro', 'English', 55566677, 'EFGH789', 'Pacific Oil Transport Inc.', 'A1+', 120000.8, '2023-04-10', 'International Maritime Organization', TRUE, 'UNDER_REVIEW'),
('Mediterranean Queen', 'Greece', 'Cruise Ship', 'Athens', 'Greek, English, French', 33344455, 'IJKL012', 'Mediterranean Cruises SA', 'A1', 0.0, '2023-07-05', 'European Maritime Safety Agency', FALSE, 'ACTIVE'),
('Baltic Trader', 'Estonia', 'General Cargo', 'Tallinn', 'Estonian, Russian, English', 99900011, 'MNOP345', 'Baltic Trading Company', 'B1', 28500.2, '2022-12-12', 'Estonian Maritime Administration', FALSE, 'MAINTENANCE');

-- Additional ship data
INSERT INTO ship_info (ship_name, ship_flag, ship_type, port_of_registry, working_languages, official_registry_number, call_sign, ship_owner, class, cargo_capacity_tonnage, date_of_ssa, ssa_by, is_high_risk_crew, ship_status)
VALUES
('Arctic Voyager', 'Japan', 'General Cargo', 'Shanghai', 'Japanese', 60427372, 'QEDJUZ', 'Maritime Ventures', 'A1', 172321.12, '2023-02-10', 'Maritime Safety Authority', FALSE, 'ACTIVE'),
('Neptune''s Pride', 'Italy', 'Oil Tanker', 'Tokyo', 'Chinese', 89045863, '12D3U1', 'Oceanic Holdings', 'A1+', 90898.02, '2023-02-18', 'International Maritime Organization', FALSE, 'ACTIVE'),
('Titanic II', 'Spain', 'General Cargo', 'Shanghai', 'English', 46137557, '0A4OCG', 'Global Freight Corp.', 'B2', 193847.41, '2023-07-06', 'Maritime Safety Authority', FALSE, 'ACTIVE'),
('Sea Phantom', 'Japan', 'Container Ship', 'Monrovia', 'Chinese', 46676731, '7CNQQI', 'Silver Line Shipping', 'B2', 112709.63, '2023-06-26', 'International Maritime Organization', TRUE, 'MAINTENANCE'),
('Poseidon''s Wrath', 'France', 'General Cargo', 'New York', 'French', 15937181, 'YHDHNF', 'Oceanic Holdings', 'A1', 58020.46, '2023-06-21', 'Maritime Safety Authority', FALSE, 'UNDER_REVIEW'),
('Black Pearl', 'Spain', 'Bulk Carrier', 'New York', 'Russian', 41227119, 'TYGOVZ', 'Silver Line Shipping', 'A1', 140652.39, '2023-04-01', 'International Maritime Organization', FALSE, 'MAINTENANCE'),
('Solar Wind', 'Japan', 'Container Ship', 'Tokyo', 'English', 89855706, 'VA9V8B', 'Global Freight Corp.', 'A1', 91765.92, '2023-02-08', 'Maritime Safety Authority', TRUE, 'ACTIVE'),
('Emerald Horizon', 'Italy', 'Oil Tanker', 'New York', 'Spanish', 91634536, 'HW0C1Z', 'Global Freight Corp.', 'C3', 193199.75, '2023-07-13', 'International Maritime Organization', TRUE, 'UNDER_REVIEW');

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

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (6, 'health', 55, 'Insufficient life saving equipment for passenger capacity', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (7, 'physical_dmg', 40, 'Minor structural damage to cargo hold bulkheads', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (8, 'cyber', 65, 'Outdated navigation and communication systems, vulnerable to cyber threats', TRUE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (9, 'violence', 35, 'Security protocols need updating, crew training recommended', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (10, 'other', 25, 'Minor compliance issues with international maritime regulations', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (11, 'illegal', 70, 'Previous cargo documentation inconsistencies require additional scrutiny', TRUE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (12, 'health', 45, 'Medical supplies inventory below required standards', FALSE);

INSERT INTO ssa_assessments (file_id, ssa_type, ssa_score, ssa_comments, is_suspect)
VALUES (13, 'physical_dmg', 60, 'Engine room maintenance issues, potential safety hazard', FALSE);

-- Sample data for infractions table
INSERT INTO infractions (file_id, infraction_type, details, infraction_date)
VALUES
(1, 'documentation_issue', 'Missing updated crew manifest', '2023-08-10'),
(3, 'physical_dmg', 'Damaged ballast water management system', '2023-07-22'),
(3, 'documentation_issue', 'Expired safety certificates', '2023-06-15'),
(4, 'custom', 'Insufficient life saving equipment for passenger capacity', '2023-09-05'),
(5, 'physical_dmg', 'Corrosion on port side hull plating', '2023-04-18');

-- Additional infractions for ships 6-13
INSERT INTO infractions (file_id, infraction_type, details, infraction_date)
VALUES
(6, 'documentation_issue', 'Incomplete cargo manifest documentation', '2023-03-15'),
(7, 'physical_dmg', 'Damaged cargo hold bulkheads requiring repair', '2023-04-22'),
(8, 'documentation_issue', 'Outdated cybersecurity compliance certificates', '2023-07-30'),
(8, 'custom', 'Unauthorized software on navigation systems', '2023-08-02'),
(9, 'physical_dmg', 'Malfunctioning emergency communication equipment', '2023-06-18'),
(10, 'documentation_issue', 'Missing crew certification for hazardous materials handling', '2023-07-05'),
(11, 'custom', 'Inconsistencies in cargo weight declarations', '2023-05-12'),
(11, 'documentation_issue', 'Expired international safety management certificate', '2023-06-28'),
(12, 'physical_dmg', 'Defective fire suppression system in engine room', '2023-03-09'),
(13, 'physical_dmg', 'Structural issues in engine mounting system', '2023-08-15'),
(13, 'custom', 'Non-compliant fuel mixture used in auxiliary engines', '2023-08-17');

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
(5, 'Stockholm Port', 'Sweden', '2023-03-18', '2023-03-22', FALSE, NULL),
(6, 'Port of Yokohama', 'Japan', '2023-02-15', '2023-02-20', FALSE, NULL),
(6, 'Busan Port', 'South Korea', '2023-02-25', '2023-03-01', FALSE, NULL),
(7, 'Port of Naples', 'Italy', '2023-01-10', '2023-01-15', FALSE, NULL),
(7, 'Port of Piraeus', 'Greece', '2023-01-20', '2023-01-25', FALSE, NULL),
(8, 'Port of Valencia', 'Spain', '2023-07-05', '2023-07-10', TRUE, NULL),
(8, 'Port of Algeciras', 'Spain', '2023-07-15', '2023-07-20', TRUE, NULL),
(9, 'Port of Hamburg', 'Germany', '2023-05-12', '2023-05-18', FALSE, NULL),
(9, 'Port of Antwerp', 'Belgium', '2023-05-22', '2023-05-28', FALSE, NULL),
(10, 'Port of New York', 'United States', '2023-06-10', '2023-06-16', FALSE, NULL),
(10, 'Port of Miami', 'United States', '2023-06-20', '2023-06-26', FALSE, NULL),
(11, 'Port of Santos', 'Brazil', '2023-04-05', '2023-04-12', TRUE, NULL),
(11, 'Port of Buenos Aires', 'Argentina', '2023-04-18', '2023-04-25', TRUE, NULL),
(12, 'Port of Dubai', 'UAE', '2023-02-08', '2023-02-14', FALSE, NULL),
(12, 'Port of Mumbai', 'India', '2023-02-20', '2023-02-26', FALSE, NULL),
(13, 'Port of Cape Town', 'South Africa', '2023-07-01', '2023-07-08', TRUE, NULL),
(13, 'Port of Durban', 'South Africa', '2023-07-15', '2023-07-22', TRUE, NULL);
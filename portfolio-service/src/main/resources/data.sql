INSERT INTO portfolio_category (id, name, code) VALUES
(1, 'Aggressive Portfolio', 'AP'),
(2, 'Defensive Portfolio', 'DP'),
(3, 'Income Portfolio', 'IP'),
(4, 'Speculative Portfolio', 'SP'),
(5, 'Hybrid Portfolio', 'HP');


INSERT INTO  investment_type (id, name, code) VALUES
(1, 'Mutual Fund', 'MF'),
(2, 'Stock', 'ST'),
(3, 'Real Estate', 'RE'),
(4, 'Trade', 'TD'),
(5, 'Commodity', 'CD'),
(6, 'Derivative', 'DR');

INSERT INTO portfolio_asset_type (id, name, code) VALUES
(1, 'State Bond', 'SBD'),
(2, 'Treasury Bill', 'TB'),
(3, 'Equity', 'EQ'),
(4, 'Commercial Paper', 'CP'),
(5, 'Federal Government Bond', 'FGN');


INSERT INTO portfolio_asset_category (id, name, code) VALUES
(1, 'Fixed Income', 'FI'),
(2, 'Bond', 'BD');
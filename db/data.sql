-- Data for Presentation

PRAGMA foreign_keys = ON;

INSERT INTO Sites (role, name, location) VALUES
  ('hq', 'Global Headquater', 'The Universe'),
  ('ss', 'Example Supplier 1', 'The Earth'),
  ('ss', 'Example Supplier 2', 'The Moon'),
  ('bs', 'Example Building Site 1', 'The Mars'),
  ('bs', 'Example Building Site 2', 'The Venus');

INSERT INTO Users (role, userid, name, password, association, phone) VALUES
  ('manager', 'admin', 'Administrator', '$argon2i$v=19$m=4096,t=3,p=1$Y29tcDYyMzk$Q10cNi3nyOvpH1JA997FaUwV8VHY5NUXtRcQ35o1r1c', (SELECT id FROM Sites WHERE name = 'Global Headquater'), '10000'),
  ('driver', 'foo', 'Mr. Foo', '$argon2i$v=19$m=4096,t=3,p=1$Y29tcDYyMzk$EJQ2nz1t3MECBAlLUiK1z4CcCMkL4RcbXN31RaMbg8g', NULL, '0-21853'),
  ('driver', 'bar', 'Ms. Bar', '$argon2i$v=19$m=4096,t=3,p=1$Y29tcDYyMzk$bObEch5K66oO85/88dtMzRwonk6JUatiMzNGu2Dvum0', NULL, '0-32618'),
  ('ss_admin', 'alice', 'Miss. Alice', '$argon2i$v=19$m=4096,t=3,p=1$Y29tcDYyMzk$q7SymIEaAKkSMngU6bD1T3ATwDhHsMDQllnanVZnCSc', (SELECT id FROM Sites WHERE name = 'Example Supplier 1'), '1-24195'),
  ('bs_admin', 'bob', 'Mr. Bob', '$argon2i$v=19$m=4096,t=3,p=1$Y29tcDYyMzk$zpykd8Vln3Y8mlcAJ3iq4UmYLmYpb+2gT+gR6qJe0gM', (SELECT id FROM Sites WHERE name = 'Example Building Site 1'), '2-41350');

INSERT INTO Packages (status, source, destination, driver) VALUES
  ('pending', (SELECT id FROM Sites WHERE name = 'Example Supplier 2'), (SELECT id FROM Sites WHERE name = 'Example Building Site 1'), (SELECT id FROM Users WHERE userid = 'foo')),
  ('ready', (SELECT id FROM Sites WHERE name = 'Example Supplier 1'), (SELECT id FROM Sites WHERE name = 'Example Building Site 1'), (SELECT id FROM Users WHERE userid = 'foo')),
  ('delivering', (SELECT id FROM Sites WHERE name = 'Example Supplier 1'), (SELECT id FROM Sites WHERE name = 'Example Building Site 2'), (SELECT id FROM Users WHERE userid = 'bar')),
  ('delivering', (SELECT id FROM Sites WHERE name = 'Example Supplier 2'), (SELECT id FROM Sites WHERE name = 'Example Building Site 1'), (SELECT id FROM Users WHERE userid = 'foo')),
  ('delivered', (SELECT id FROM Sites WHERE name = 'Example Supplier 1'), (SELECT id FROM Sites WHERE name = 'Example Building Site 2'), (SELECT id FROM Users WHERE userid = 'bar')),
  ('cancelled', (SELECT id FROM Sites WHERE name = 'Example Supplier 2'), (SELECT id FROM Sites WHERE name = 'Example Building Site 2'), (SELECT id FROM Users WHERE userid = 'foo')),
  ('cancelled', (SELECT id FROM Sites WHERE name = 'Example Supplier 1'), (SELECT id FROM Sites WHERE name = 'Example Building Site 1'), (SELECT id FROM Users WHERE userid = 'foo'));

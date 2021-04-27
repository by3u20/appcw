-- Table Creation Statements for SQLite

-- Sites (inc. Supplier Sites, Building Sites)
CREATE TABLE IF NOT EXISTS SiteRoles (
  id    TEXT PRIMARY KEY,
  title TEXT
);

INSERT INTO SiteRoles (id, title) VALUES
  ('hq', 'Headquater'),
  ('ss', 'Supplier Site'),
  ('bs', 'Building Site');

CREATE TABLE IF NOT EXISTS Sites (
  id       INTEGER PRIMARY KEY,
  role     TEXT REFERENCES SiteRoles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  name     TEXT,
  location TEXT
);

-- Users (inc. Managers, Drivers, Supplier Admins, Building Site Admins)
CREATE TABLE IF NOT EXISTS UserRoles (
  id    TEXT PRIMARY KEY,
  title TEXT
);

INSERT INTO UserRoles (id, title) VALUES
  ('manager', 'Manager'),
  ('driver', 'Driver'),
  ('ss_admin', 'Supplier Site Administrator'),
  ('bs_admin', 'Building Site Administrator');

CREATE TABLE IF NOT EXISTS Users (
  id          INTEGER PRIMARY KEY,
  role        TEXT REFERENCES UserRoles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  userid      TEXT UNIQUE,
  name        TEXT,
  password    TEXT,
  association INTEGER REFERENCES Sites (id) ON DELETE SET NULL ON UPDATE CASCADE,
  phone       TEXT
);

-- Packages / Deliveries
CREATE TABLE IF NOT EXISTS PackageStatuses (
  id    TEXT PRIMARY KEY,
  title TEXT
);

INSERT INTO PackageStatuses (id, title) VALUES
  ('pending', 'Pending'),
  ('ready', 'Ready'),
  ('delivering', 'Delivering'),
  ('delivered', 'Delivered'),
  ('cancelled', 'Cancelled');

CREATE TABLE IF NOT EXISTS Packages (
  id          INTEGER PRIMARY KEY,
  status      TEXT REFERENCES PackageStatuses (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  source      INTEGER REFERENCES Sites (id) ON DELETE SET NULL ON UPDATE CASCADE,
  destination INTEGER REFERENCES Sites (id) ON DELETE SET NULL ON UPDATE CASCADE,
  driver      INTEGER REFERENCES Users (id) ON DELETE SET NULL ON UPDATE CASCADE
);

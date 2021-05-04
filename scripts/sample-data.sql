-- Sample Data

INSERT INTO `Sites` (`role`, `name`, `location`) VALUES
  -- ('HQ', 'The Headquater', 'The Universe'),
  ('SS', 'Example Supplier Site 1', 'The Earth'),
  ('SS', 'Example Supplier Site 2', 'The Moon'),
  ('SS', 'Example Supplier Site 3', 'The Mars'),
  ('BS', 'Example Building Site 1', 'The Mercury'),
  ('BS', 'Example Building Site 2', 'The Jupiter');

INSERT INTO `Users` (`role`, `user_id`, `username`, `password`) VALUES
  ('MANAGER', 'admin', 'Dr. Manager', '$argon2i$v=19$m=4096,t=3,p=1$Y29tLmV4YW1wbGUuZGVsaXZlcnkx$Ou4CFuht9i47AR8hdKKui4b64Oe3+kc0/bhxfD++W40'),
  ('DRIVER', 'foo', 'B. Foo', '$argon2i$v=19$m=4096,t=3,p=1$Y29tLmV4YW1wbGUuZGVsaXZlcnkx$XR42PlYSYy/FCkmG2cmX+MxJWgd8u08WQuQqLsY1jSw'),
  ('DRIVER', 'carol', 'Carol', '$argon2i$v=19$m=4096,t=3,p=1$Y29tLmV4YW1wbGUuZGVsaXZlcnkx$hVJAq8p6/ejoOe/paRHRcmkYHrKInqVrDVy9ZD++CfU'),
  ('SS_ADMIN', 'alice', 'Alice', '$argon2i$v=19$m=4096,t=3,p=1$Y29tLmV4YW1wbGUuZGVsaXZlcnkx$xjr/dc3DBcUORkrUSYr5NNEZ7/DKvPnQVWYlGm4djXY'),
  ('BS_ADMIN', 'bob', 'Bob', '$argon2i$v=19$m=4096,t=3,p=1$Y29tLmV4YW1wbGUuZGVsaXZlcnkx$qUAHQzLQVOy1yc+cHfzC2pdAI6wpwU5vFp6gu23xcXM');

INSERT INTO `Deliveries` (`status`, `from_id`, `to_id`, `driver_id`) VALUES
  ('PENDING', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 1'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 1'), (SELECT id FROM `Users` WHERE `user_id` = 'foo')),
  ('READY', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 2'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 1'), (SELECT id FROM `Users` WHERE `user_id` = 'foo')),
  ('READY', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 1'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 2'), (SELECT id FROM `Users` WHERE `user_id` = 'carol')),
  ('DELIVERING', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 2'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 2'), (SELECT id FROM `Users` WHERE `user_id` = 'carol')),
  ('DELIVERING', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 3'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 1'), (SELECT id FROM `Users` WHERE `user_id` = 'foo')),
  ('DELIVERED', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 1'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 2'), (SELECT id FROM `Users` WHERE `user_id` = 'carol')),
  ('DELIVERED', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 3'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 2'), (SELECT id FROM `Users` WHERE `user_id` = 'carol')),
  ('DELIVERED', (SELECT id FROM `Sites` WHERE `name` = 'Example Supplier Site 2'), (SELECT id FROM `Sites` WHERE `name` = 'Example Building Site 1'), (SELECT id FROM `Users` WHERE `user_id` = 'foo'));

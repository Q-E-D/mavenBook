CREATE TABLE account(
  id INT IDENTITY PRIMARY,
  name VARCHAR(32),
  email VARCHAR(32),
  password VARCHAR(32),
  activited BIT(1)
);
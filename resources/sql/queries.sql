-- name:get-messages
-- selects all available messages
SELECT * from guestbook

-- name:get-message
-- selects one message by id
SELECT * from guestbook
WHERE id = :id

-- name:save-message!
-- creates a new message
INSERT INTO guestbook
(name, message, timestamp)
VALUES (:name, :message, :timestamp)

-- name:delete-message!
-- delete a message by id
DELETE from guestbook
WHERE id = :id

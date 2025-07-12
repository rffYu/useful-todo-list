CREATE TABLE todo_item (
    todo_id VARCHAR(50) PRIMARY KEY,
    user_id VARCHAR(50),
    title VARCHAR(255),
    content TEXT,
    done BOOLEAN DEFAULT FALSE
);


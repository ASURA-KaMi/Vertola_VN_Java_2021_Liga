CREATE TABLE school
    (
      id UUID NOT NULL PRIMARY KEY,
      title VARCHAR (255) NOT NULL,
      address VARCHAR (255) NOT NULL
    );
CREATE TABLE users
    (
      id UUID NOT NULL PRIMARY KEY,
      full_name VARCHAR (255) NOT NULL,
      age INTEGER NOT NULL,
      sex VARCHAR (10) NOT NULL,
      school_id UUID NOT NULL,
      FOREIGN KEY (school_id) references school (id) ON DELETE SET NULL
    );
CREATE TABLE posts
    (
      id UUID NOT NULL,
      post_data VARCHAR (1024)
    );
CREATE TABLE users_posts
    (
      users_id UUID,
      posts_id UUID,
      user_id UUID NOT NULL,
      FOREIGN KEY (user_id) references users (id) ON DELETE CASCADE,
      FOREIGN KEY (posts_id) references posts (id),
      PRIMARY KEY (user_id,posts_id)
    );
CREATE TABLE friends
(
    users_id UUID,
    friends_id UUID,
    FOREIGN KEY (users_id) references users (id) ON DELETE CASCADE,
    FOREIGN KEY (friends_id) references users (id),
    PRIMARY KEY (users_id,friends_id)
);
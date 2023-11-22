CREATE TABLE `books` (
  `id` INT PRIMARY KEY auto_increment,
  `title` VARCHAR(100) not null,
  `category_id` INT not null,
  `author` VARCHAR(400),
  `publishing_date` datetime,
  `quantity` INT not null,
  `date_added` datetime not null,
  `librarian_id` INT not null,
  `image` varchar(100),
  `limit_date` INT not null,
  `deleted_at` datetime,
  `updated_at` datetime
);

CREATE TABLE `students` (
  `id` INT PRIMARY KEY auto_increment,
  `code` char(7) not null,
  `name` varchar(50) not null,
  `major` varchar(100) not null
);

CREATE TABLE `book_student` (
  `id` BIGINT PRIMARY KEY auto_increment,
  `student_id` INT  not null,
  `book_id` INT  not null,
  `start_date` datetime  not null,
  `end_date` datetime,
  `librarian_id` int not null
);

CREATE TABLE `librarians` (
  `id` INT PRIMARY KEY auto_increment,
  `name` varchar(50)  not null,
  `email` varchar(100)  not null,
  `password` varchar(100)  not null,
  `role` int  not null,
  `deleted_at` datetime
);

CREATE TABLE `category_book` (
  `id` INT PRIMARY KEY auto_increment,
  `name` varchar(80)  not null
);

ALTER TABLE `books` ADD FOREIGN KEY (`category_id`) REFERENCES `category_book` (`id`);

ALTER TABLE `books` ADD FOREIGN KEY (`librarian_id`) REFERENCES `librarians` (`id`);

ALTER TABLE `book_student` ADD FOREIGN KEY (`student_id`) REFERENCES `students` (`id`);

ALTER TABLE `book_student` ADD FOREIGN KEY (`book_id`) REFERENCES `books` (`id`);

ALTER TABLE `book_student` ADD FOREIGN KEY (`librarian_id`) REFERENCES `librarians` (`id`);

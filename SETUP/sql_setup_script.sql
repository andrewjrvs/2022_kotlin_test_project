CREATE TABLE `wrd_wordy` (
	`wrd_id` INT NOT NULL,
	`wrd_message` VARCHAR(5000) DEFAULT NULL,
	`wrd_count` INT NOT NULL DEFAULT '0',
	PRIMARY KEY (`wrd_id`)
);

GO;
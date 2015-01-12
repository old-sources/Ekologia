-- NEVER CHANGE A FILE WHICH IS COMMITTED IN THIS DIRECTORY
-- User/Account modification
ALTER TABLE "user" RENAME TO "account";

ALTER TABLE "account" ADD COLUMN salt character varying(255);
ALTER TABLE "account" ADD COLUMN discriminator character varying(255);
ALTER TABLE "account" ADD COLUMN firstname character varying(255);
ALTER TABLE "account" ADD COLUMN lastname character varying(255);
ALTER TABLE "account" ADD COLUMN birthday timestamp;
ALTER TABLE "account" ADD COLUMN orgname character varying(255);
ALTER TABLE "account" ADD COLUMN activity character varying(255);
ALTER TABLE "account" ADD COLUMN type character varying(255);
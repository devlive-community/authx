/**
  Update 1.7.0
 */
ALTER TABLE `authx_menu`
    DROP COLUMN `method`;
ALTER TABLE `authx_menu`
    ADD COLUMN `is_system` BOOLEAN DEFAULT FALSE;

DROP VIEW IF EXISTS view_node_ref_and_id;
CREATE VIEW view_node_ref_and_id AS
	SELECT
		CONCAT(alf_store.protocol, '://', alf_store.identIFier, '/', alf_node.uuid) AS 'NodeRef',
		alf_node.id AS 'ID'
	FROM
		alf_node
		INNER JOIN alf_store ON alf_node.store_id = alf_store.id
	WHERE
		alf_store.protocol = 'workspace'
;
DROP VIEW IF EXISTS view_associations_by_type;
CREATE VIEW view_associations_by_type AS
	SELECT
		view_source.NodeRef as 'SourceNodeRef',
		alf_qname.local_name COLLATE utf8_general_ci AS 'AssociationType',
		view_target.NodeRef as 'TargetNodeRef'
	FROM
		alf_node_assoc AS ANA
	JOIN view_node_ref_and_id AS view_source ON ANA.source_node_id = view_source.ID
	JOIN view_node_ref_and_id AS view_target ON ANA.target_node_id = view_target.ID
	JOIN alf_qname ON alf_qname.id = ANA.type_qname_id;


--
-- Record script finish
--
DELETE FROM alf_applied_patch WHERE id = 'patch.db-V5.0-AssociationFilterViews';
INSERT INTO alf_applied_patch
(id, description, fixes_from_schema, fixes_to_schema, applied_to_schema, target_schema, applied_on_date, applied_to_server, was_executed, succeeded, report)
VALUES
	(
		'patch.db-V5.0-AssociationFilterViews', 'Manually executed script for V5.0: Create Association Filter Database Views',
		0, 8022, -1, 9999, null, 'UNKNOWN', ${TRUE}, ${TRUE}, 'Script completed'
	);
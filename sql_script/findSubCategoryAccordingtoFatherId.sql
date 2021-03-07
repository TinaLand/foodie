SELECT 
    f.id as id,
    f.`name` as Name,
    f.type as type,
    f.father_id as fatherId,
    
	c.id as subId,
    c.`name` as subName,
    f.type as subType,
    f.father_id as subFatherId
    
FROM
    `foodie-shop-dev`.category f

LEFT JOIN
	`foodie-shop-dev`.category c
ON
	f.id = c.father_id
WHERE
	f.father_id = 1
SELECT
    f.id as rootCatId,
    f.`name` as rootCatName,
    f.slogan as slogan,
    f.cat_image as catImage,
    f.bg_color as bgColor,
    i.id as itemId,
    i.item_name as itemName,
    ii.url as itemUrl,
    i.created_time as createdTime
    
FROM 
	`foodie-shop-dev`.category f
LEFT JOIN
	`foodie-shop-dev`.items i
ON
	f.id = i.root_cat_id
LEFT JOIN
	`foodie-shop-dev`.items_img ii
ON
	i.id = ii.item_id
WHERE 
	f.type = 1
AND
	i.root_cat_id = 7
AND 
	ii.is_main = 1
ORDER BY 
	i.created_time 
DESC
LIMIT 0, 6
	
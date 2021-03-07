SELECT
	i.id as itemId,
    i.item_name as itemName,
    i.sell_counts as sellCounts,
    ii.url as imgUrl
    
From 
	`foodie-shop-dev`.items i
LEFT JOIN
	`foodie-shop-dev`.items_img ii
ON
	i.id = ii.item_id
WHERE
	ii.is_main = 1;
    
SELECT 
	item_id, MIN(price_discount) as price_discount
FROM
	items_param
GROUP BY
	item_id
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信配置', '3', '1', '/wx/wxConfig', 'C', '0', 'wx:wxConfig:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '微信配置菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信配置查询', @parentId, '1',  '#',  'F', '0', 'wx:wxConfig:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信配置新增', @parentId, '2',  '#',  'F', '0', 'wx:wxConfig:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信配置修改', @parentId, '3',  '#',  'F', '0', 'wx:wxConfig:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信配置删除', @parentId, '4',  '#',  'F', '0', 'wx:wxConfig:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('微信配置导出', @parentId, '5',  '#',  'F', '0', 'wx:wxConfig:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

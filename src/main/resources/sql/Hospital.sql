FROM nation_wide_hospitals
where business_type_name in ('보건소', '보건지소');

SELECT * FROM nation_wide_hospitals
where business_type_name in ('보건소', '보건지소', '보건진료소')
  and road_name_address like '%광진구%';
create table system_metrics(
	id int,
	Time_stamp timestamp,
	service_name varchar(255),
	cpu_usage float,
	memory_usage float,
	active_threads int
);

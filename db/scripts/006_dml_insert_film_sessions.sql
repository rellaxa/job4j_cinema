-------------------------------------------------------------
-- Заполнение таблицы film_sessions (сеансы фильмов)
-------------------------------------------------------------
-- Session for film_id 1: The Fall Guy (2024) – Comedy in Hall 1
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    1, -- The Fall Guy
    1, -- Hall 1
    '2024-06-01 14:00:00',
    '2024-06-01 15:50:00', -- 110 minutes later
    10
);

-- Session for film_id 2: Cruella (2021) – Comedy in VIP Hall
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    2, -- Cruella
    2, -- VIP Hall
    '2024-06-01 16:00:00',
    '2024-06-01 18:14:00', -- 134 minutes later
    12
);

-- Session for film_id 3: Interstellar (2014) – Sci‑fi in Hall 1
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    3, -- Interstellar
    1, -- Hall 1
    '2024-06-01 18:30:00',
    '2024-06-01 21:19:00', -- 169 minutes later
    10
);

-- Session for film_id 4: Blade Runner 2049 (2017) – Sci‑fi in IMAX Hall
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    4, -- Blade Runner 2049
    3, -- IMAX Hall
    '2024-06-02 19:00:00',
    '2024-06-02 21:44:00', -- 164 minutes later
    15
);

-- Session for film_id 5: Murder on the Orient Express (2017) – Detective in Hall 1
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    5, -- Murder on the Orient Express
    1, -- Hall 1
    '2024-06-01 19:00:00',
    '2024-06-01 20:54:00', -- 114 minutes later
    10
);

-- Session for film_id 6: Knives Out (2019) – Detective in VIP Hall
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    6, -- Knives Out
    2, -- VIP Hall
    '2024-06-01 20:00:00',
    '2024-06-01 22:10:00', -- 130 minutes later
    12
);

-- Session for film_id 7: IT (2017) – Horror in Hall 1
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    7, -- IT
    1, -- Hall 1
    '2024-06-02 23:00:00',
    '2024-06-03 01:15:00', -- 135 minutes later
    8
);

-- Session for film_id 8: Nosferatu (2024) – Horror in VIP Hall
insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values(
    8, -- Nosferatu
    2, -- VIP Hall
    '2024-06-03 21:00:00',
    '2024-06-03 22:35:00', -- 95 minutes later
    9
);
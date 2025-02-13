-------------------------------------------------------------
-- Заполнение таблицы films
-------------------------------------------------------------
-- 1. The Fall Guy (2024) – Comedy, file_id = 1, genre_id = 3 (comedy)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'The Fall Guy',
    'A down-on-his-luck stuntman finds himself thrust into a high-octane world of danger and humor as
    he takes on risky jobs to save his career. This 2024 comedy reinvents the classic tale with thrilling stunts and quirky characters.',
    2024,
    3,
    12,
    110,
    1
);

-- 2. Cruella (2021) – Comedy, file_id = 2, genre_id = 3 (comedy)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'Cruella',
    'A stylish and rebellious prequel exploring the origin of the notorious fashion villain Cruella de Vil.
    Blending dark humor and crime, "Cruella" (2021) offers a fresh, witty take on a classic character.',
    2021,
    3,
    12,
    134,
    2
);

-- 3. Interstellar (2014) – Sci‑fi, file_id = 3, genre_id = 2 (sci-fi)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'Interstellar',
    'In a future where Earth is nearing collapse, a team of explorers embarks on a journey through
    a wormhole to find a new home for humanity. This epic 2014 sci‑fi adventure challenges the boundaries of time and space.',
    2014,
    2,
    13,
    169,
    3
);

-- 4. Blade Runner 2049 (2017) – Sci‑fi, file_id = 4, genre_id = 2 (sci-fi)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'Blade Runner 2049',
    'Set in a dystopian future, a new blade runner unearths a long-buried secret that could disrupt
    society and blur the line between humans and replicants. This 2017 sequel deepens the mystery of its iconic predecessor.',
    2017,
    2,
    16,
    164,
    4
);

-- 5. Murder on the Orient Express (2017) – Detective, file_id = 5, genre_id = 1 (detective)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'Murder on the Orient Express',
    'Famed detective Hercule Poirot investigates a murder aboard the luxurious Orient Express,
    where every passenger becomes a suspect. This 2017 adaptation of the classic tale is filled with twists and elegant intrigue.',
    2017,
    1,
    13,
    114,
    5
);

-- 6. Knives Out (2019) – Detective, file_id = 6, genre_id = 1 (detective)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'Knives Out',
    'A modern twist on the classic whodunit, detective Benoit Blanc unravels the mystery behind
    the sudden death of a wealthy patriarch. This 2019 film is a clever, stylish, and intricately plotted murder mystery.',
    2019,
    1,
    13,
    130,
    6
);

-- 7. IT (2017) – Horror, file_id = 7, genre_id = 4 (horror)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'IT',
    'Based on Stephen King’s iconic novel, a group of children faces their deepest fears when
    a shape-shifting entity emerges from the sewers. This 2017 horror film combines terror with
    a coming-of-age drama in a small-town setting.',
    2017,
    4,
    16,
    135,
    7
);

-- 8. Nosferatu (2024) – Horror, file_id = 8, genre_id = 4 (horror)
insert into films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id)
values(
    'Nosferatu',
    'A modern reimagining of the classic vampire legend, "Nosferatu" (2024) delves into the dark,
    eerie world of the undead with atmospheric visuals and a haunting narrative that redefines horror.',
    2024,
    4,
    16,
    95,
    8
);
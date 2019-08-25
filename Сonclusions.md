
### 128M
||Serial GC|Parallel GC|G1 GC|
|---|---|---|---|
|Минорных сборок, шт|41|49|123|
|Среднее время минорной сборки, мс|19|21|10|
|Минорных сборок в минуту, 1/мин|55.96|67.57|110.56|
|Мажорных сборок, шт|36|45|4|
|Среднее время мажорной сборки, мс|190|331|130|
|Мажорных сборок в минуту, 1/мин|49.79|53.89|10.82|
|Время до OutMemoryError, с|72.127|78.861|66.852|

### 512M
||Serial GC|Parallel GC|G1 GC|
|---|---|---|---|
|Минорных сборок, шт|32|52|68|
|Среднее время минорной сборки, мс|54|84|18|
|Минорных сборок в минуту, 1/мин|13.23|16.20|18.02|
|Мажорных сборок, шт|18|16|2|
|Среднее время мажорной сборки, мс|752|1138|259|
|Мажорных сборок в минуту, 1/мин|9.88|9.7|142.80|
|Время до OutMemoryError, с|229.751|237.376|226.759|

### 1024M
||Serial GC|Parallel GC|G1 GC|
|---|---|---|---|
|Минорных сборок, шт|37|46|106|
|Среднее время минорной сборки, мс|81|131|22|
|Минорных сборок в минуту, 1/мин|6.72|8.27|12.5|
|Мажорных сборок, шт|26|28|2|
|Среднее время мажорной сборки, мс|1421|2186|295|
|Мажорных сборок в минуту, 1/мин|5.15|5.27|191.39|
|Время до OutMemoryError, с|532.785|552.778|509.807|

---

### Выводы:
#### Продолжительность сборок в минуту
| |Serial GC|Parallel GC|G1 GC|
|---|---|---|---|
|Время минорных сборок за минуту, 128M, мс|1063 (1.77%)|1418 (2.36%)|1105 (1.84%)|
|Время минорных сборок за минуту, 512M, мс|714 (1.19%)|1360 (2.27%)|324 (0.54%)|
|Время минорных сборок за минуту, 1024M, мс|544 (0.91%)|1083 (1.81%)|275 (0.46%)|
|Время мажорных сборок за минуту, 128M, мс|9460 (15.8%)|17837 (29.7%)|1406 (2.34%)|
|Время мажорных сборок за минуту, 512M, мс|7429 (12.4%)|11038 (18.4%)|36985 (61.7%)|
|Время мажорных сборок за минуту, 1024M, мс|7318 (12.2%)|11520 (19.2%)|56460 (94.1%)|

#### Время работы до MemoryOutError
| |Serial GC|Parallel GC|G1 GC|
|---|---|---|----|
|128 M|72.127 (с)|78.861 (с)|66.852 (с)|
|512 M|229.751 (c)|237.376 (c)|226.759 (c)|
|1024 M|532.785 (c)|552.785 (c)|509.807 (c)|

#### Плюсы Serial GC
+ Serial GC имеет хорошие показатели продолжительности минорных сборок от 0.91% до 1.77% от общего времени.
+ Serial GC имеет лучшие показатели продолжительности мажорных сборок в минуту от 12.2% до 15.8% от общего времени.

#### Минусы Serial GC
+ Serial GC имеет худший показатель *время работы до OutMemoryError*, чем Parallel GC. 

#### Плюсы Parallel GC
+ Parallel GC имеет лучший показатель *время работы до OutMemoryError* .

#### Минусы Parallel GC
+ Parallel GC имеет показатели продолжительности и минорных, и мажорных сборок, хуже чем у Serial GC.

#### Плюсы G1 GC
+ G1 GC имеет лучие показатели продолжительности минорных сборок от 0.46% до 1.84% от общего времени. 

#### Минусы G1 GC
+ G1 GC имеет худшие показатели продолжительности мажорных сборок от 2.34% до 94.1% от общего времени.
+ G1 GC имеет худший показатель *время работы до MemoryOutError*.

#### Выводы по Parallel GC
Единственный показатель, по которому Parallel GC имел в ходе данных тестов лучший результат - это
*время до OutMemoryError*. С учетом того, что в конце работы приложения идут постоянные мажорные
сборки, что приводит к низкому уровню отклика приложения, разницу между данными показателями
у разных GC можно считать ничножной и не учитывать.

#### Выводы по G1 GC
G1 GC показал лучшие результаты продолжительности минорных сборок, что даёт право полагать,
что применение G1 GC к другому приложению, без настолько скоростного заполнения памяти, дало бы
лучшие результаты, чем применение Serial GC.

#### Выводы по Serial GC
В ходе тестов **лучший результат показал Serial GC**, у данного GC  лучший общий показатель 
времени сборок, что обеспечит высокий уровень отклика приложения.
---

### -XX:+UseSerialGC -Xms128m -Xmx128m 

startTime|gcAction|gcCause|duration
---|---|---|---
1181|end of minor GC|Allocation Failure|10
2234|end of minor GC|Allocation Failure|12
3329|end of minor GC|Allocation Failure|7
4478|end of minor GC|Allocation Failure|14
5528|end of minor GC|Allocation Failure|31
6701|end of minor GC|Allocation Failure|28
7869|end of minor GC|Allocation Failure|22
8925|end of minor GC|Allocation Failure|16
10085|end of minor GC|Allocation Failure|20
11236|end of minor GC|Allocation Failure|12
12169|end of minor GC|Allocation Failure|29
13376|end of minor GC|Allocation Failure|11
14522|end of minor GC|Allocation Failure|7
15678|end of minor GC|Allocation Failure|13
16866|end of minor GC|Allocation Failure|8
18004|end of minor GC|Allocation Failure|9
18790|end of minor GC|Allocation Failure|40
20007|end of minor GC|Allocation Failure|11
21143|end of minor GC|Allocation Failure|7
22282|end of minor GC|Allocation Failure|29
23500|end of minor GC|Allocation Failure|23
24650|end of minor GC|Allocation Failure|8
25840|end of minor GC|Allocation Failure|27
27011|end of minor GC|Allocation Failure|7
27950|end of minor GC|Allocation Failure|16
28649|end of minor GC|Allocation Failure|34
28683|end of major GC|Allocation Failure|131
29962|end of minor GC|Allocation Failure|13
31112|end of minor GC|Allocation Failure|20
32325|end of minor GC|Allocation Failure|22
33483|end of minor GC|Allocation Failure|11
34650|end of minor GC|Allocation Failure|11
35854|end of minor GC|Allocation Failure|8
37000|end of minor GC|Allocation Failure|11
38154|end of minor GC|Allocation Failure|11
39356|end of minor GC|Allocation Failure|19
40521|end of minor GC|Allocation Failure|21
41682|end of minor GC|Allocation Failure|27
42539|end of minor GC|Allocation Failure|8
42917|end of minor GC|Allocation Failure|49
42966|end of major GC|Allocation Failure|156
43430|end of minor GC|Allocation Failure|58
43488|end of major GC|Allocation Failure|157
43959|end of minor GC|Allocation Failure|68
44027|end of major GC|Allocation Failure|182
45520|end of major GC|Allocation Failure|163
46921|end of major GC|Allocation Failure|173
48292|end of major GC|Allocation Failure|169
49598|end of major GC|Allocation Failure|169
50863|end of major GC|Allocation Failure|172
52070|end of major GC|Allocation Failure|180
53226|end of major GC|Allocation Failure|183
54347|end of major GC|Allocation Failure|205
55436|end of major GC|Allocation Failure|186
56510|end of major GC|Allocation Failure|177
57524|end of major GC|Allocation Failure|185
58485|end of major GC|Allocation Failure|192
59418|end of major GC|Allocation Failure|192
60337|end of major GC|Allocation Failure|189
61201|end of major GC|Allocation Failure|198
62024|end of major GC|Allocation Failure|203
62847|end of major GC|Allocation Failure|202
63602|end of major GC|Allocation Failure|208
64383|end of major GC|Allocation Failure|200
65111|end of major GC|Allocation Failure|197
65828|end of major GC|Allocation Failure|201
66547|end of major GC|Allocation Failure|202
67202|end of major GC|Allocation Failure|197
67879|end of major GC|Allocation Failure|197
68495|end of major GC|Allocation Failure|207
69115|end of major GC|Allocation Failure|206
69728|end of major GC|Allocation Failure|200
70290|end of major GC|Allocation Failure|202
70848|end of major GC|Allocation Failure|206
71414|end of major GC|Allocation Failure|203
71863|end of major GC|Allocation Failure|200
72064|end of major GC|Allocation Failure|251


+ Количество минорных сборок : 41
+ Среднее время минорной сборки : 19 (мс)
+ Количество минорных сборок в минуту : 55,96 (1/мин)
+ Количество мажорных сборок : 36
+ Среднее время мажорной сборки : 190 (мс)
+ Количество мажорных сборок в минуту : 49,79 (1/мин)
+ Время до OutMemoryError : 72.127 (c)

### -XX:+UseParallelGC -Xms128m -Xmx128m 

startTime|gcAction|gcCause|duration
---|---|---|---
1094|end of minor GC|Allocation Failure|17
2083|end of minor GC|Allocation Failure|10
3131|end of minor GC|Allocation Failure|11
4231|end of minor GC|Allocation Failure|11
5221|end of minor GC|Allocation Failure|27
6329|end of minor GC|Allocation Failure|10
7155|end of minor GC|Allocation Failure|8
7823|end of minor GC|Allocation Failure|10
8647|end of minor GC|Allocation Failure|11
9479|end of minor GC|Allocation Failure|38
10299|end of minor GC|Allocation Failure|33
11054|end of minor GC|Allocation Failure|15
11795|end of minor GC|Allocation Failure|15
12330|end of minor GC|Allocation Failure|28
12985|end of minor GC|Allocation Failure|29
13633|end of minor GC|Allocation Failure|27
14277|end of minor GC|Allocation Failure|16
14911|end of minor GC|Allocation Failure|31
15621|end of minor GC|Allocation Failure|12
16314|end of minor GC|Allocation Failure|28
17007|end of minor GC|Allocation Failure|28
17754|end of minor GC|Allocation Failure|27
18510|end of minor GC|Allocation Failure|18
18942|end of minor GC|Allocation Failure|26
19742|end of minor GC|Allocation Failure|13
20533|end of minor GC|Allocation Failure|19
21381|end of minor GC|Allocation Failure|20
22283|end of minor GC|Allocation Failure|24
23187|end of minor GC|Allocation Failure|14
24084|end of minor GC|Allocation Failure|20
24989|end of minor GC|Allocation Failure|36
25957|end of minor GC|Allocation Failure|18
26964|end of minor GC|Allocation Failure|21
27974|end of minor GC|Allocation Failure|22
28464|end of minor GC|Allocation Failure|25
28489|end of major GC|Ergonomics|228
29760|end of minor GC|Allocation Failure|18
30819|end of minor GC|Allocation Failure|35
31945|end of minor GC|Allocation Failure|18
32996|end of minor GC|Allocation Failure|22
34165|end of minor GC|Allocation Failure|33
35289|end of minor GC|Allocation Failure|31
36467|end of minor GC|Allocation Failure|21
37582|end of minor GC|Allocation Failure|20
38745|end of minor GC|Allocation Failure|22
39914|end of minor GC|Allocation Failure|34
41092|end of minor GC|Allocation Failure|37
42269|end of minor GC|Allocation Failure|24
43071|end of minor GC|Allocation Failure|19
43509|end of minor GC|Allocation Failure|24
43533|end of major GC|Ergonomics|293
44973|end of major GC|Ergonomics|324
46378|end of major GC|Ergonomics|275
47702|end of major GC|Ergonomics|275
48966|end of major GC|Ergonomics|279
50180|end of major GC|Ergonomics|271
51374|end of major GC|Ergonomics|293
52543|end of major GC|Ergonomics|298
53663|end of major GC|Ergonomics|288
54725|end of major GC|Ergonomics|314
55762|end of major GC|Ergonomics|297
56778|end of major GC|Ergonomics|296
57749|end of major GC|Ergonomics|297
58670|end of major GC|Ergonomics|314
59598|end of major GC|Ergonomics|318
60489|end of major GC|Ergonomics|321
61383|end of major GC|Ergonomics|326
62225|end of major GC|Ergonomics|327
63071|end of major GC|Ergonomics|312
63853|end of major GC|Ergonomics|326
64645|end of major GC|Ergonomics|317
65381|end of major GC|Ergonomics|318
66115|end of major GC|Ergonomics|345
66825|end of major GC|Ergonomics|357
67548|end of major GC|Ergonomics|338
68252|end of major GC|Ergonomics|342
68908|end of major GC|Ergonomics|344
69564|end of major GC|Ergonomics|341
70219|end of major GC|Ergonomics|345
70873|end of major GC|Ergonomics|358
71489|end of major GC|Ergonomics|373
72121|end of major GC|Ergonomics|348
72727|end of major GC|Ergonomics|350
73282|end of major GC|Ergonomics|377
73865|end of major GC|Ergonomics|381
74454|end of major GC|Ergonomics|368
75027|end of major GC|Ergonomics|363
75595|end of major GC|Ergonomics|366
76169|end of major GC|Ergonomics|363
76689|end of major GC|Ergonomics|393
77240|end of major GC|Ergonomics|365
77759|end of major GC|Ergonomics|377
78239|end of major GC|Ergonomics|356
78595|end of major GC|Allocation Failure|439


+ Количество минорных сборок : 49
+ Среднее время минорной сборки : 21 (мс)
+ Количество минорных сборок в минуту : 67,57 (1/мин)
+ Количество мажорных сборок : 45
+ Среднее время мажорной сборки : 331 (мс)
+ Количество мажорных сборок в минуту : 53,89 (1/мин)
+ Время до OutMemoryError : 78.861 (c)

### -XX:+UseG1GC -Xms128m -Xmx128m 

startTime|gcAction|gcCause|duration
---|---|---|---
910|end of minor GC|G1 Evacuation Pause|10
2122|end of minor GC|G1 Evacuation Pause|13
3346|end of minor GC|G1 Evacuation Pause|36
5128|end of minor GC|G1 Evacuation Pause|18
7707|end of minor GC|G1 Evacuation Pause|19
10110|end of minor GC|G1 Evacuation Pause|45
12170|end of minor GC|G1 Evacuation Pause|17
14215|end of minor GC|G1 Evacuation Pause|27
15913|end of minor GC|G1 Evacuation Pause|13
17540|end of minor GC|G1 Evacuation Pause|9
18982|end of minor GC|G1 Humongous Allocation|15
20492|end of minor GC|G1 Evacuation Pause|20
20564|end of minor GC|G1 Evacuation Pause|12
22241|end of minor GC|G1 Evacuation Pause|10
23870|end of minor GC|G1 Evacuation Pause|21
25498|end of minor GC|G1 Evacuation Pause|25
27021|end of minor GC|G1 Evacuation Pause|13
28479|end of minor GC|G1 Evacuation Pause|13
28599|end of minor GC|G1 Evacuation Pause|22
28892|end of minor GC|G1 Humongous Allocation|4
30434|end of minor GC|G1 Evacuation Pause|10
31474|end of minor GC|G1 Evacuation Pause|9
32437|end of minor GC|G1 Evacuation Pause|20
32511|end of minor GC|G1 Evacuation Pause|9
33711|end of minor GC|G1 Evacuation Pause|8
34844|end of minor GC|G1 Evacuation Pause|19
34915|end of minor GC|G1 Evacuation Pause|9
36113|end of minor GC|G1 Evacuation Pause|11
37261|end of minor GC|G1 Evacuation Pause|19
38410|end of minor GC|G1 Evacuation Pause|11
39486|end of minor GC|G1 Evacuation Pause|7
40516|end of minor GC|G1 Evacuation Pause|9
41559|end of minor GC|G1 Evacuation Pause|11
42537|end of minor GC|G1 Evacuation Pause|6
43520|end of minor GC|G1 Evacuation Pause|19
43802|end of minor GC|G1 Humongous Allocation|7
44465|end of minor GC|G1 Evacuation Pause|59
44681|end of minor GC|G1 Evacuation Pause|11
44694|end of minor GC|G1 Evacuation Pause|6
44700|end of major GC|G1 Evacuation Pause|117
45575|end of minor GC|G1 Evacuation Pause|9
46287|end of minor GC|G1 Evacuation Pause|11
46888|end of minor GC|G1 Evacuation Pause|11
47487|end of minor GC|G1 Evacuation Pause|8
48134|end of minor GC|G1 Evacuation Pause|7
48733|end of minor GC|G1 Evacuation Pause|9
49340|end of minor GC|G1 Evacuation Pause|9
49928|end of minor GC|G1 Evacuation Pause|9
50473|end of minor GC|G1 Evacuation Pause|13
50978|end of minor GC|G1 Evacuation Pause|19
51482|end of minor GC|G1 Evacuation Pause|13
51931|end of minor GC|G1 Evacuation Pause|13
52362|end of minor GC|G1 Evacuation Pause|9
52756|end of minor GC|G1 Evacuation Pause|14
53140|end of minor GC|G1 Evacuation Pause|7
53477|end of minor GC|G1 Evacuation Pause|13
53861|end of minor GC|G1 Evacuation Pause|3
54192|end of minor GC|G1 Evacuation Pause|14
54525|end of minor GC|G1 Evacuation Pause|11
54850|end of minor GC|G1 Evacuation Pause|8
55120|end of minor GC|G1 Evacuation Pause|15
55458|end of minor GC|G1 Evacuation Pause|10
55734|end of minor GC|G1 Evacuation Pause|8
55966|end of minor GC|G1 Evacuation Pause|12
56239|end of minor GC|G1 Evacuation Pause|9
56467|end of minor GC|G1 Evacuation Pause|9
56688|end of minor GC|G1 Evacuation Pause|4
56914|end of minor GC|G1 Evacuation Pause|12
57142|end of minor GC|G1 Evacuation Pause|5
57308|end of minor GC|G1 Evacuation Pause|3
57471|end of minor GC|G1 Evacuation Pause|9
57648|end of minor GC|G1 Evacuation Pause|11
57820|end of minor GC|G1 Evacuation Pause|3
58038|end of minor GC|G1 Evacuation Pause|6
58210|end of minor GC|G1 Evacuation Pause|6
58374|end of minor GC|G1 Evacuation Pause|4
58540|end of minor GC|G1 Evacuation Pause|11
58762|end of minor GC|G1 Evacuation Pause|6
58928|end of minor GC|G1 Evacuation Pause|4
59094|end of minor GC|G1 Evacuation Pause|9
59261|end of minor GC|G1 Evacuation Pause|4
59473|end of minor GC|G1 Evacuation Pause|1
59633|end of minor GC|G1 Evacuation Pause|10
59801|end of minor GC|G1 Evacuation Pause|4
59964|end of minor GC|G1 Evacuation Pause|4
60181|end of minor GC|G1 Evacuation Pause|5
60354|end of minor GC|G1 Evacuation Pause|9
60523|end of minor GC|G1 Evacuation Pause|4
60687|end of minor GC|G1 Evacuation Pause|6
60911|end of minor GC|G1 Evacuation Pause|5
61076|end of minor GC|G1 Evacuation Pause|3
61236|end of minor GC|G1 Evacuation Pause|6
61402|end of minor GC|G1 Evacuation Pause|4
61564|end of minor GC|G1 Evacuation Pause|4
61779|end of minor GC|G1 Evacuation Pause|5
61944|end of minor GC|G1 Evacuation Pause|6
62107|end of minor GC|G1 Evacuation Pause|3
62274|end of minor GC|G1 Evacuation Pause|10
62491|end of minor GC|G1 Evacuation Pause|2
62655|end of minor GC|G1 Evacuation Pause|8
62820|end of minor GC|G1 Evacuation Pause|3
62984|end of minor GC|G1 Evacuation Pause|8
63207|end of minor GC|G1 Evacuation Pause|9
63384|end of minor GC|G1 Evacuation Pause|9
63554|end of minor GC|G1 Evacuation Pause|3
63713|end of minor GC|G1 Evacuation Pause|7
63936|end of minor GC|G1 Evacuation Pause|4
64098|end of minor GC|G1 Evacuation Pause|3
64258|end of minor GC|G1 Evacuation Pause|4
64417|end of minor GC|G1 Evacuation Pause|5
64639|end of minor GC|G1 Evacuation Pause|13
64817|end of minor GC|G1 Evacuation Pause|21
64840|end of minor GC|G1 Evacuation Pause|8
64848|end of major GC|G1 Evacuation Pause|135
65201|end of minor GC|G1 Evacuation Pause|5
65425|end of minor GC|G1 Evacuation Pause|5
65591|end of minor GC|G1 Evacuation Pause|4
65752|end of minor GC|G1 Evacuation Pause|8
65921|end of minor GC|G1 Evacuation Pause|12
66093|end of minor GC|G1 Evacuation Pause|4
66303|end of minor GC|G1 Evacuation Pause|6
66473|end of minor GC|G1 Evacuation Pause|6
66637|end of minor GC|G1 Evacuation Pause|3
66745|end of minor GC|G1 Humongous Allocation|4
66750|end of minor GC|G1 Humongous Allocation|2
66752|end of major GC|G1 Humongous Allocation|131
66883|end of major GC|G1 Humongous Allocation|137


+ Количество минорных сборок : 123
+ Среднее время минорной сборки : 10 (мс)
+ Количество минорных сборок в минуту : 110,56 (1/мин)
+ Количество мажорных сборок : 4
+ Среднее время мажорной сборки : 130 (мс)
+ Количество мажорных сборок в минуту : 10,82 (1/мин)
+ Время до OutMemoryError : 66.852 (c)

### -XX:+UseSerialGC -Xms512m -Xmx512m 

startTime|gcAction|gcCause|duration
---|---|---|---
4415|end of minor GC|Allocation Failure|22
8680|end of minor GC|Allocation Failure|45
12810|end of minor GC|Allocation Failure|35
17180|end of minor GC|Allocation Failure|32
21445|end of minor GC|Allocation Failure|60
26077|end of minor GC|Allocation Failure|47
30112|end of minor GC|Allocation Failure|103
34857|end of minor GC|Allocation Failure|54
39484|end of minor GC|Allocation Failure|31
43190|end of minor GC|Allocation Failure|90
47879|end of minor GC|Allocation Failure|59
52529|end of minor GC|Allocation Failure|53
57204|end of minor GC|Allocation Failure|57
61844|end of minor GC|Allocation Failure|44
65306|end of minor GC|Allocation Failure|101
70076|end of minor GC|Allocation Failure|57
74744|end of minor GC|Allocation Failure|39
79379|end of minor GC|Allocation Failure|40
83986|end of minor GC|Allocation Failure|44
88679|end of minor GC|Allocation Failure|38
93296|end of minor GC|Allocation Failure|40
96175|end of minor GC|Allocation Failure|123
100916|end of minor GC|Allocation Failure|58
105564|end of minor GC|Allocation Failure|55
110232|end of minor GC|Allocation Failure|57
114953|end of minor GC|Allocation Failure|50
119580|end of minor GC|Allocation Failure|0
119580|end of major GC|Allocation Failure|661
124852|end of minor GC|Allocation Failure|14
129412|end of minor GC|Allocation Failure|40
133942|end of minor GC|Allocation Failure|37
138565|end of minor GC|Allocation Failure|39
143165|end of minor GC|Allocation Failure|41
145167|end of minor GC|Allocation Failure|128
149920|end of minor GC|Allocation Failure|0
149920|end of major GC|Allocation Failure|604
155148|end of minor GC|Allocation Failure|0
155148|end of major GC|Allocation Failure|579
160346|end of minor GC|Allocation Failure|0
160346|end of major GC|Allocation Failure|717
165698|end of minor GC|Allocation Failure|0
165698|end of major GC|Allocation Failure|574
170876|end of minor GC|Allocation Failure|0
170876|end of major GC|Allocation Failure|622
176072|end of minor GC|Allocation Failure|0
176072|end of major GC|Allocation Failure|635
181349|end of minor GC|Allocation Failure|0
181349|end of major GC|Allocation Failure|731
186638|end of minor GC|Allocation Failure|0
186638|end of major GC|Allocation Failure|730
192631|end of major GC|Allocation Failure|724
198398|end of major GC|Allocation Failure|733
203886|end of major GC|Allocation Failure|992
209547|end of major GC|Allocation Failure|766
214678|end of major GC|Allocation Failure|748
219635|end of major GC|Allocation Failure|832
224451|end of major GC|Allocation Failure|971
227986|end of major GC|Allocation Failure|882
228868|end of major GC|Allocation Failure|1050


+ Количество минорных сборок : 32
+ Среднее время минорной сборки : 54 (мс)
+ Количество минорных сборок в минуту : 13,23 (1/мин)
+ Количество мажорных сборок : 18
+ Среднее время мажорной сборки : 752 (мс)
+ Количество мажорных сборок в минуту : 9,88 (1/мин)
+ Время до OutMemoryError : 229.751 (c)

### -XX:+UseParallelGC -Xms512m -Xmx512m 

startTime|gcAction|gcCause|duration
---|---|---|---
4217|end of minor GC|Allocation Failure|14
8171|end of minor GC|Allocation Failure|20
11901|end of minor GC|Allocation Failure|25
15841|end of minor GC|Allocation Failure|29
19790|end of minor GC|Allocation Failure|31
23965|end of minor GC|Allocation Failure|29
27061|end of minor GC|Allocation Failure|25
29606|end of minor GC|Allocation Failure|40
32719|end of minor GC|Allocation Failure|39
35869|end of minor GC|Allocation Failure|47
38931|end of minor GC|Allocation Failure|99
42044|end of minor GC|Allocation Failure|65
44192|end of minor GC|Allocation Failure|37
47084|end of minor GC|Allocation Failure|52
49901|end of minor GC|Allocation Failure|67
52780|end of minor GC|Allocation Failure|69
55707|end of minor GC|Allocation Failure|61
58584|end of minor GC|Allocation Failure|68
61622|end of minor GC|Allocation Failure|65
63876|end of minor GC|Allocation Failure|66
65877|end of minor GC|Allocation Failure|50
69000|end of minor GC|Allocation Failure|72
71779|end of minor GC|Allocation Failure|71
74619|end of minor GC|Allocation Failure|64
77598|end of minor GC|Allocation Failure|72
80577|end of minor GC|Allocation Failure|74
83825|end of minor GC|Allocation Failure|75
87079|end of minor GC|Allocation Failure|73
90492|end of minor GC|Allocation Failure|80
93963|end of minor GC|Allocation Failure|81
96747|end of minor GC|Allocation Failure|76
98713|end of minor GC|Allocation Failure|99
102622|end of minor GC|Allocation Failure|70
106498|end of minor GC|Allocation Failure|95
110661|end of minor GC|Allocation Failure|90
114809|end of minor GC|Allocation Failure|93
119129|end of minor GC|Allocation Failure|105
123461|end of minor GC|Allocation Failure|102
127989|end of minor GC|Allocation Failure|102
132537|end of minor GC|Allocation Failure|96
137228|end of minor GC|Allocation Failure|99
137327|end of major GC|Ergonomics|1616
143627|end of minor GC|Allocation Failure|64
148374|end of major GC|Ergonomics|707
153678|end of minor GC|Allocation Failure|83
158428|end of minor GC|Allocation Failure|120
163102|end of minor GC|Allocation Failure|156
167849|end of minor GC|Allocation Failure|165
172335|end of minor GC|Allocation Failure|148
176839|end of minor GC|Allocation Failure|192
180847|end of minor GC|Allocation Failure|199
184942|end of minor GC|Allocation Failure|171
188712|end of minor GC|Allocation Failure|204
192564|end of minor GC|Allocation Failure|196
192761|end of major GC|Ergonomics|1857
198054|end of major GC|Ergonomics|1185
202264|end of major GC|Ergonomics|1173
206312|end of major GC|Ergonomics|1150
210226|end of major GC|Ergonomics|1155
213990|end of major GC|Ergonomics|1132
217630|end of major GC|Ergonomics|1113
221088|end of major GC|Ergonomics|948
224283|end of major GC|Ergonomics|964
227387|end of major GC|Ergonomics|961
230384|end of major GC|Ergonomics|957
233274|end of major GC|Ergonomics|984
235245|end of major GC|Ergonomics|1003
236248|end of major GC|Allocation Failure|1303


+ Количество минорных сборок : 52
+ Среднее время минорной сборки : 84 (мс)
+ Количество минорных сборок в минуту : 16,20 (1/мин)
+ Количество мажорных сборок : 16
+ Среднее время мажорной сборки : 1138 (мс)
+ Количество мажорных сборок в минуту : 9,70 (1/мин)
+ Время до OutMemoryError : 237.376 (c)

### -XX:+UseG1GC -Xms512m -Xmx512m 

startTime|gcAction|gcCause|duration
---|---|---|---
921|end of minor GC|G1 Evacuation Pause|8
2109|end of minor GC|G1 Evacuation Pause|9
3343|end of minor GC|G1 Evacuation Pause|19
5255|end of minor GC|G1 Evacuation Pause|10
8140|end of minor GC|G1 Evacuation Pause|17
11510|end of minor GC|G1 Evacuation Pause|20
16044|end of minor GC|G1 Evacuation Pause|26
26585|end of minor GC|G1 Evacuation Pause|37
35867|end of minor GC|G1 Evacuation Pause|53
44770|end of minor GC|G1 Evacuation Pause|58
54015|end of minor GC|G1 Evacuation Pause|47
62833|end of minor GC|G1 Evacuation Pause|37
71470|end of minor GC|G1 Evacuation Pause|42
78459|end of minor GC|G1 Evacuation Pause|38
85189|end of minor GC|G1 Evacuation Pause|19
91632|end of minor GC|G1 Evacuation Pause|43
100262|end of minor GC|G1 Evacuation Pause|28
108447|end of minor GC|G1 Evacuation Pause|49
114134|end of minor GC|G1 Evacuation Pause|28
119483|end of minor GC|G1 Evacuation Pause|19
119764|end of minor GC|G1 Evacuation Pause|27
125967|end of minor GC|G1 Evacuation Pause|11
132058|end of minor GC|G1 Evacuation Pause|20
138039|end of minor GC|G1 Evacuation Pause|24
143768|end of minor GC|G1 Evacuation Pause|34
144017|end of minor GC|G1 Evacuation Pause|15
149546|end of minor GC|G1 Evacuation Pause|14
154694|end of minor GC|G1 Evacuation Pause|65
155634|end of minor GC|G1 Evacuation Pause|5
156452|end of minor GC|G1 Evacuation Pause|8
157191|end of minor GC|G1 Evacuation Pause|6
157955|end of minor GC|G1 Evacuation Pause|9
158785|end of minor GC|G1 Evacuation Pause|10
161358|end of minor GC|G1 Evacuation Pause|11
164034|end of minor GC|G1 Evacuation Pause|13
166827|end of minor GC|G1 Evacuation Pause|13
169600|end of minor GC|G1 Evacuation Pause|14
170160|end of minor GC|G1 Evacuation Pause|13
173065|end of minor GC|G1 Evacuation Pause|10
176018|end of minor GC|G1 Evacuation Pause|15
178988|end of minor GC|G1 Evacuation Pause|16
181855|end of minor GC|G1 Evacuation Pause|15
184696|end of minor GC|G1 Evacuation Pause|15
187510|end of minor GC|G1 Evacuation Pause|13
188066|end of minor GC|G1 Evacuation Pause|12
190758|end of minor GC|G1 Evacuation Pause|10
193368|end of minor GC|G1 Evacuation Pause|13
195938|end of minor GC|G1 Evacuation Pause|13
198372|end of minor GC|G1 Evacuation Pause|13
198989|end of minor GC|G1 Evacuation Pause|13
201356|end of minor GC|G1 Evacuation Pause|9
203539|end of minor GC|G1 Evacuation Pause|7
205646|end of minor GC|G1 Evacuation Pause|13
207638|end of minor GC|G1 Evacuation Pause|21
208310|end of minor GC|G1 Evacuation Pause|10
210220|end of minor GC|G1 Evacuation Pause|11
212089|end of minor GC|G1 Evacuation Pause|11
213909|end of minor GC|G1 Evacuation Pause|12
215571|end of minor GC|G1 Evacuation Pause|8
217211|end of minor GC|G1 Evacuation Pause|9
218769|end of minor GC|G1 Evacuation Pause|10
220191|end of minor GC|G1 Evacuation Pause|13
221570|end of minor GC|G1 Evacuation Pause|10
222346|end of minor GC|G1 Evacuation Pause|7
223658|end of minor GC|G1 Evacuation Pause|8
224881|end of minor GC|G1 Evacuation Pause|10
226146|end of minor GC|G1 Evacuation Pause|8
226416|end of minor GC|G1 Humongous Allocation|4
226420|end of major GC|G1 Humongous Allocation|271
226691|end of major GC|G1 Humongous Allocation|248


+ Количество минорных сборок : 68
+ Среднее время минорной сборки : 18 (мс)
+ Количество минорных сборок в минуту : 18,02 (1/мин)
+ Количество мажорных сборок : 2
+ Среднее время мажорной сборки : 259 (мс)
+ Количество мажорных сборок в минуту : 142,80 (1/мин)
+ Время до OutMemoryError : 226.759 (c)

### -XX:+UseSerialGC -Xms1024m -Xmx1024m 

startTime|gcAction|gcCause|duration
---|---|---|---
8801|end of minor GC|Allocation Failure|36
17552|end of minor GC|Allocation Failure|61
26479|end of minor GC|Allocation Failure|51
34261|end of minor GC|Allocation Failure|62
42535|end of minor GC|Allocation Failure|62
51812|end of minor GC|Allocation Failure|71
60985|end of minor GC|Allocation Failure|47
69113|end of minor GC|Allocation Failure|117
78436|end of minor GC|Allocation Failure|81
87791|end of minor GC|Allocation Failure|80
96089|end of minor GC|Allocation Failure|76
103723|end of minor GC|Allocation Failure|130
113131|end of minor GC|Allocation Failure|76
122434|end of minor GC|Allocation Failure|71
131825|end of minor GC|Allocation Failure|72
141197|end of minor GC|Allocation Failure|76
147851|end of minor GC|Allocation Failure|168
157283|end of minor GC|Allocation Failure|79
166689|end of minor GC|Allocation Failure|68
176084|end of minor GC|Allocation Failure|75
185409|end of minor GC|Allocation Failure|76
194790|end of minor GC|Allocation Failure|75
204173|end of minor GC|Allocation Failure|78
213561|end of minor GC|Allocation Failure|72
218870|end of minor GC|Allocation Failure|212
228398|end of minor GC|Allocation Failure|0
228398|end of major GC|Allocation Failure|992
238720|end of minor GC|Allocation Failure|28
247968|end of minor GC|Allocation Failure|52
257338|end of minor GC|Allocation Failure|74
266682|end of minor GC|Allocation Failure|71
276045|end of minor GC|Allocation Failure|74
285435|end of minor GC|Allocation Failure|70
294781|end of minor GC|Allocation Failure|69
304162|end of minor GC|Allocation Failure|69
313506|end of minor GC|Allocation Failure|72
322900|end of minor GC|Allocation Failure|70
327132|end of minor GC|Allocation Failure|76
330461|end of minor GC|Allocation Failure|220
330681|end of major GC|Allocation Failure|1247
341202|end of minor GC|Allocation Failure|0
341202|end of major GC|Allocation Failure|1179
353121|end of major GC|Allocation Failure|1458
363830|end of minor GC|Allocation Failure|0
363831|end of major GC|Allocation Failure|1223
375569|end of major GC|Allocation Failure|1271
386947|end of major GC|Allocation Failure|1295
397893|end of major GC|Allocation Failure|1565
408649|end of major GC|Allocation Failure|1324
418746|end of major GC|Allocation Failure|1338
428416|end of major GC|Allocation Failure|1343
437713|end of major GC|Allocation Failure|1590
446934|end of major GC|Allocation Failure|1380
455331|end of major GC|Allocation Failure|1409
463657|end of major GC|Allocation Failure|1394
471601|end of major GC|Allocation Failure|1420
479330|end of major GC|Allocation Failure|1423
486744|end of major GC|Allocation Failure|1455
493927|end of major GC|Allocation Failure|1451
500852|end of major GC|Allocation Failure|1471
507514|end of major GC|Allocation Failure|1482
514027|end of major GC|Allocation Failure|1501
520278|end of major GC|Allocation Failure|1501
526304|end of major GC|Allocation Failure|1840
529546|end of major GC|Allocation Failure|1569
531115|end of major GC|Allocation Failure|1836


+ Количество минорных сборок : 37
+ Среднее время минорной сборки : 81 (мс)
+ Количество минорных сборок в минуту : 6,72 (1/мин)
+ Количество мажорных сборок : 26
+ Среднее время мажорной сборки : 1421 (мс)
+ Количество мажорных сборок в минуту : 5,15 (1/мин)
+ Время до OutMemoryError : 532.785 (c)

### -XX:+UseParallelGC -Xms1024m -Xmx1024m 

startTime|gcAction|gcCause|duration
---|---|---|---
8212|end of minor GC|Allocation Failure|28
15449|end of minor GC|Allocation Failure|33
23613|end of minor GC|Allocation Failure|46
31693|end of minor GC|Allocation Failure|59
40396|end of minor GC|Allocation Failure|52
47821|end of minor GC|Allocation Failure|63
54024|end of minor GC|Allocation Failure|50
60171|end of minor GC|Allocation Failure|56
65397|end of minor GC|Allocation Failure|78
71672|end of minor GC|Allocation Failure|75
77947|end of minor GC|Allocation Failure|90
84337|end of minor GC|Allocation Failure|122
90350|end of minor GC|Allocation Failure|117
96344|end of minor GC|Allocation Failure|122
100494|end of minor GC|Allocation Failure|81
106299|end of minor GC|Allocation Failure|109
111927|end of minor GC|Allocation Failure|118
117565|end of minor GC|Allocation Failure|112
123303|end of minor GC|Allocation Failure|115
129106|end of minor GC|Allocation Failure|115
135035|end of minor GC|Allocation Failure|134
141035|end of minor GC|Allocation Failure|134
145495|end of minor GC|Allocation Failure|116
149239|end of minor GC|Allocation Failure|148
156085|end of minor GC|Allocation Failure|113
162727|end of minor GC|Allocation Failure|151
170006|end of minor GC|Allocation Failure|142
177216|end of minor GC|Allocation Failure|154
184966|end of minor GC|Allocation Failure|167
192719|end of minor GC|Allocation Failure|156
200843|end of minor GC|Allocation Failure|164
209040|end of minor GC|Allocation Failure|168
217605|end of minor GC|Allocation Failure|182
222181|end of minor GC|Allocation Failure|221
231137|end of minor GC|Allocation Failure|145
231282|end of major GC|Ergonomics|2463
242692|end of minor GC|Allocation Failure|119
251762|end of minor GC|Allocation Failure|187
261036|end of minor GC|Allocation Failure|181
270436|end of minor GC|Allocation Failure|195
280077|end of minor GC|Allocation Failure|197
289731|end of minor GC|Allocation Failure|197
299624|end of minor GC|Allocation Failure|199
309518|end of minor GC|Allocation Failure|214
319529|end of minor GC|Allocation Failure|217
329646|end of minor GC|Allocation Failure|203
333740|end of minor GC|Allocation Failure|196
333936|end of major GC|Ergonomics|2396
346087|end of major GC|Ergonomics|2604
358672|end of major GC|Ergonomics|1605
370259|end of major GC|Ergonomics|2217
382268|end of major GC|Ergonomics|2274
393872|end of major GC|Ergonomics|2309
405048|end of major GC|Ergonomics|2275
415767|end of major GC|Ergonomics|1835
425516|end of major GC|Ergonomics|1842
434969|end of major GC|Ergonomics|1860
444078|end of major GC|Ergonomics|1900
452911|end of major GC|Ergonomics|1946
461412|end of major GC|Ergonomics|1988
469655|end of major GC|Ergonomics|1938
477523|end of major GC|Ergonomics|2013
485127|end of major GC|Ergonomics|2041
492531|end of major GC|Ergonomics|2048
499571|end of major GC|Ergonomics|2616
507046|end of major GC|Ergonomics|2594
514310|end of major GC|Ergonomics|2107
520854|end of major GC|Ergonomics|2105
527102|end of major GC|Ergonomics|2716
533755|end of major GC|Ergonomics|2179
539739|end of major GC|Ergonomics|2155
545497|end of major GC|Ergonomics|2176
547933|end of major GC|Ergonomics|2169
550102|end of major GC|Allocation Failure|2845


+ Количество минорных сборок : 46
+ Среднее время минорной сборки : 131 (мс)
+ Количество минорных сборок в минуту : 8,27 (1/мин)
+ Количество мажорных сборок : 28
+ Среднее время мажорной сборки : 2186 (мс)
+ Количество мажорных сборок в минуту : 5,27 (1/мин)
+ Время до OutMemoryError : 552.778 (c)

### -XX:+UseG1GC -Xms1024m -Xmx1024m 

startTime|gcAction|gcCause|duration
---|---|---|---
1902|end of minor GC|G1 Evacuation Pause|10
3351|end of minor GC|G1 Evacuation Pause|28
5464|end of minor GC|G1 Evacuation Pause|14
7894|end of minor GC|G1 Evacuation Pause|9
12668|end of minor GC|G1 Evacuation Pause|27
17772|end of minor GC|G1 Evacuation Pause|31
24056|end of minor GC|G1 Evacuation Pause|22
33012|end of minor GC|G1 Evacuation Pause|39
42594|end of minor GC|G1 Evacuation Pause|41
54717|end of minor GC|G1 Evacuation Pause|50
67863|end of minor GC|G1 Evacuation Pause|62
82869|end of minor GC|G1 Evacuation Pause|63
100677|end of minor GC|G1 Evacuation Pause|91
118641|end of minor GC|G1 Evacuation Pause|73
136140|end of minor GC|G1 Evacuation Pause|67
153104|end of minor GC|G1 Evacuation Pause|76
166741|end of minor GC|G1 Evacuation Pause|70
179763|end of minor GC|G1 Evacuation Pause|56
192062|end of minor GC|G1 Evacuation Pause|42
207685|end of minor GC|G1 Evacuation Pause|45
223006|end of minor GC|G1 Evacuation Pause|45
226625|end of minor GC|G1 Humongous Allocation|39
241581|end of minor GC|G1 Evacuation Pause|48
252092|end of minor GC|G1 Evacuation Pause|56
262467|end of minor GC|G1 Evacuation Pause|40
274947|end of minor GC|G1 Evacuation Pause|37
286746|end of minor GC|G1 Evacuation Pause|39
297999|end of minor GC|G1 Evacuation Pause|35
308720|end of minor GC|G1 Evacuation Pause|39
318977|end of minor GC|G1 Evacuation Pause|34
328725|end of minor GC|G1 Evacuation Pause|36
338020|end of minor GC|G1 Evacuation Pause|46
340028|end of minor GC|G1 Humongous Allocation|28
347179|end of minor GC|G1 Evacuation Pause|102
349146|end of minor GC|G1 Evacuation Pause|16
350849|end of minor GC|G1 Evacuation Pause|9
352502|end of minor GC|G1 Evacuation Pause|13
354134|end of minor GC|G1 Evacuation Pause|13
358321|end of minor GC|G1 Evacuation Pause|10
362648|end of minor GC|G1 Evacuation Pause|13
367061|end of minor GC|G1 Evacuation Pause|19
371592|end of minor GC|G1 Evacuation Pause|18
376195|end of minor GC|G1 Evacuation Pause|20
380720|end of minor GC|G1 Evacuation Pause|19
381990|end of minor GC|G1 Evacuation Pause|23
386456|end of minor GC|G1 Evacuation Pause|8
390862|end of minor GC|G1 Evacuation Pause|15
392118|end of minor GC|G1 Evacuation Pause|14
396418|end of minor GC|G1 Evacuation Pause|14
400516|end of minor GC|G1 Evacuation Pause|26
404649|end of minor GC|G1 Evacuation Pause|19
408544|end of minor GC|G1 Evacuation Pause|18
409907|end of minor GC|G1 Evacuation Pause|15
413637|end of minor GC|G1 Evacuation Pause|11
417239|end of minor GC|G1 Evacuation Pause|15
420677|end of minor GC|G1 Evacuation Pause|17
423940|end of minor GC|G1 Evacuation Pause|16
425372|end of minor GC|G1 Evacuation Pause|14
428481|end of minor GC|G1 Evacuation Pause|11
431430|end of minor GC|G1 Evacuation Pause|14
434275|end of minor GC|G1 Evacuation Pause|14
436949|end of minor GC|G1 Evacuation Pause|16
438428|end of minor GC|G1 Evacuation Pause|13
441000|end of minor GC|G1 Evacuation Pause|10
443456|end of minor GC|G1 Evacuation Pause|13
445814|end of minor GC|G1 Evacuation Pause|12
447985|end of minor GC|G1 Evacuation Pause|10
450117|end of minor GC|G1 Evacuation Pause|9
452065|end of minor GC|G1 Evacuation Pause|13
453667|end of minor GC|G1 Evacuation Pause|9
455587|end of minor GC|G1 Evacuation Pause|9
457311|end of minor GC|G1 Evacuation Pause|12
459013|end of minor GC|G1 Evacuation Pause|14
460635|end of minor GC|G1 Evacuation Pause|8
462335|end of minor GC|G1 Evacuation Pause|6
463851|end of minor GC|G1 Evacuation Pause|7
465489|end of minor GC|G1 Evacuation Pause|10
467054|end of minor GC|G1 Evacuation Pause|9
468667|end of minor GC|G1 Evacuation Pause|11
470191|end of minor GC|G1 Evacuation Pause|7
471758|end of minor GC|G1 Evacuation Pause|11
473452|end of minor GC|G1 Evacuation Pause|22
474983|end of minor GC|G1 Evacuation Pause|8
476553|end of minor GC|G1 Evacuation Pause|11
478144|end of minor GC|G1 Evacuation Pause|9
479660|end of minor GC|G1 Evacuation Pause|7
481345|end of minor GC|G1 Evacuation Pause|9
482913|end of minor GC|G1 Evacuation Pause|12
484501|end of minor GC|G1 Evacuation Pause|10
486019|end of minor GC|G1 Evacuation Pause|8
487593|end of minor GC|G1 Evacuation Pause|12
489292|end of minor GC|G1 Evacuation Pause|10
490811|end of minor GC|G1 Evacuation Pause|7
492398|end of minor GC|G1 Evacuation Pause|10
493999|end of minor GC|G1 Evacuation Pause|11
495569|end of minor GC|G1 Evacuation Pause|6
497158|end of minor GC|G1 Evacuation Pause|6
498796|end of minor GC|G1 Evacuation Pause|11
500433|end of minor GC|G1 Evacuation Pause|10
501950|end of minor GC|G1 Evacuation Pause|6
503523|end of minor GC|G1 Evacuation Pause|9
505126|end of minor GC|G1 Evacuation Pause|10
506747|end of minor GC|G1 Evacuation Pause|6
508326|end of minor GC|G1 Evacuation Pause|10
508774|end of minor GC|G1 Humongous Allocation|11
508787|end of minor GC|G1 Humongous Allocation|5
508792|end of major GC|G1 Humongous Allocation|627
509419|end of major GC|G1 Humongous Allocation|563


+ Количество минорных сборок : 106
+ Среднее время минорной сборки : 22 (мс)
+ Количество минорных сборок в минуту : 12,50 (1/мин)
+ Количество мажорных сборок : 2
+ Среднее время мажорной сборки : 295 (мс)
+ Количество мажорных сборок в минуту : 191,39 (1/мин)
+ Время до OutMemoryError : 509.807 (c)

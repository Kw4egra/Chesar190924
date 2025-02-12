/* Техническое задание
Разработать программу, позволяющую шифровать и расшифровывать текст с
использованием шифра Цезаря. Программа должна поддерживать несколько режимов работы,
обеспечивать обработку больших файлов и иметь валидацию входных данных.
Опционально можно добавить графический интерфейс пользователя, а также
 статистический анализ для автоматического взлома шифра.

Основные задачи:

Реализация шифра Цезаря:

Создание и использование алфавита.
Алгоритм сдвига символов в соответствии с заданным ключом.
Обработка файлов:

Функциональность для работы с файлами (чтение, запись).
Обработка больших текстовых файлов.
Валидация входных данных:

Проверка существования файлов.
Допустимость ключей.
Режимы работы:

Шифрование текста:

Функция шифрования, принимающая файл, ключ и записывающая зашифрованный текст в новый файл.
Расшифровка текста:

Функция расшифровки с использованием известного ключа.
Расшифровка методом brute force (опционально):

Реализация метода brute force для перебора всех ключей до успешного расшифрования.
Расшифровка методом статистического анализа (опционально):

Разработка алгоритма статистического анализа для автоматической расшифровки без ключа,
используя особенности языка.
Разработка интерфейса пользователя:

Текстовое меню или (опционально) графический интерфейс
Дополнительные задачи:

Обработка ошибок и исключений.
Оптимизация для производительности.
Документация и тестирование.
Криптология, криптография и криптоанализ
Приступим к разбору теории, которая понадобится тебе при написании итогового проекта.
Давай узнаем больше о криптологии и ее составляющих. А заодно — больше о шифре, который ты будешь использовать при написании итогового практического проекта.

Задача: написать программу, которая работает с шифром Цезаря.
Это один из самых простых и известных методов шифрования. Назвали его, само собой, в честь императора Гая Юлия Цезаря, который применял его для секретной переписки с генералами.

Шифр Цезаря — это шифр подстановки: в нем каждый символ в открытом тексте заменяется на символ,
который находится на некотором постоянном числе позиций левее или правее него в алфавите.

Допустим, мы устанавливаем сдвиг на 3 (ключ = 3). В таком случае А заменится на Г, Б станет Д, и так далее.

Давай для наглядности рассмотрим игрушку для шифрования кодом Цезаря:
Она состоит из двух кругов которые вращаются относительно друг друга.
По периметру каждого из кругов написаны буквы в порядке алфавита.
При смещении все буквы внутреннего круга смещаются на одинаковое расстояние относительно букв внешнего круга.

Для шифрования мы буквы из внешнего круга заменяем на стоящие рядом с ними буквы из нижнего круга.
Например, если круги смещены как на картинке, А заменяем на С, а R — на T.
А чтобы расшифровать все делаем наоборот: буквы из внутреннего круга заменяем на буквы из внешнего;
то есть, С обратно на А, и Т обратно на R.

Допустим, песик Бобик решил написать девочке Алисе секретное сообщение.
У каждого из них есть такая игрушка. Чтобы успешно зашифровать и расшифровать сообщение,
они должны договориться на сколько позиций надо сместить круги своих игрушек относительно друг-друга.
На картинке игрушки видно что А стала С, а В стала D, значит внутренний круг смещен на 2 позиции.
Заметь, Y стала А (и не улетела в космос). Когда они договорились сместить круги на 2 позиции и Бобик передал Алисе надпись NGVU RNCA NGIQ, Алиса сразу все поняла и пошла доставать коробку с Lego.

Так как в английском алфавите только 26 букв, можно сместить круги на 1,2,...,25 позиций.
Если мы сместим на 26 (или 0) позиций то буквы на окружностях совпадут. Можно почитать лекцию CS50 Криптография.
Шифр Цезаря и шифр Виженера

Криптоанализ — взлом шифра

Криптография — алгоритмы шифрования данных (и не только)

Алфавит — полный набор символов, которые могут попасться в тексте, поддающемся шифровке.
В шифре Цезаря важен порядок этих символов. Он может и не быть классическим порядком букв в алфавите,
но его должны знать и Бобик (тот кто шифрует) и Алиса (тот кто расшифрует).

Криптоанализ статистических данных основан на том, что в каждом языке есть своя статистика использования символов.
Например, в этом тексте (пока я его пишу) уже есть 14 слов “и” и 24 слова “в”.
Если сохранять пробелы, то легко догадаться, что наиболее частые слова из одной буквы это
скорее всего В или К или И. И если использовали шифр Цезаря с классическим порядком букв в алфавите,
расшифровать этот текст будет элементарно
(попробуем сдвиг для В — нечитабельно — тогда попробуем сдвиг для К, и так далее).

Хорошо, можно съедать пробелы, чтобы спрятать слова из 1 буквы.
Но тогда можно легко определить гласные (они встречаются намного чаще согласных),
или часто встречающиеся слова вроде ИЛИ. Даже простой перебор не составит трудностей,
так как всего существует очень маленькое число вариантов для подбора (для английского алфавита 25).
Поэтому Бобику и Алисе следует попробовать более сложные метод шифрования.

Отходить от буквального задания — можно. Главное — суть.

Это минимум теоретических данных, которые понадобятся тебе для выполнения итогового проекта.
Переходим к описанию задания!
*/
import java.io.*;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws Exception {
        Chesar.getEncrypteMessage("da.txt",7,true);
        sleep(1000);
        Chesar.GetDescriptionMessage("EncrMes.txt",7,true);
    }
}
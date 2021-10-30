# Memorize Dictionary
This is a school final project.

## 01. Motivation
In Taiwan, we have lots of chances to use English. As we grow up, English plays a more and more important role in our lives. Taking English tests for college, watching classical American series, or even presenting in English, once when we do not understand the word, we will loop up the dictionary. However, we will usually forget the meaning of the word the next day, because this type of short-term memory can not last for a long time.

Therefore, we want to develop some kind of application which is a dictionary but also provides learning tools for studying and memorizing these English words in a more efficient and easier way.
***
## 02. Introduction
In this project, we choose the people who want to take English tests as our target audience, especially Taiwanese high school students. In Taiwan, there is a test administered by College Entrance Examination Center to measure a high school student's readiness for college in subjects, and the English test is one of them. For the English test, high school students need to memorize at least **7000 words** to prepare, and the amount is quite a lot to them, therefore if this application can help them that will be awesome. As for the function part, we will have quizzes and flashcards. Additionly, users can add new words to this dictionary out of the default 7000-word folder.

***
## 03. Environment Setting
### 1. Database system
This project connected with the `phpMyAdmin` database system. However, it was closed due to the end of the semester. Thus, you need to change your database URL in the `ExecSQL`class.
```java
public ExecSQL() throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/"; //change the URL that the database you are using
		String database = "MG14";
		String config = "?useUnicode=true&characterEncoding=utf8";
		this.url= server + database + config;
		this.username= "MG14";
		this.password= "RHJX2R";
		initializing();
	}
```
### 2. Default 7000 words database
### 3. Import jar
This project is built on Eclipse. First, you need to put the jar file under your project.
Double click the project >> Properties >> Java Build Path >> Libraries >> Add External JARs >> add `mysql-connector-java-8.0.16.jar` >> apply
***
## 04. Key Functions
### Dictionary Search
![image](https://github.com/Wei-Hsi/template/blob/main/all%20project%20layout/java%20app/main%20page.png)
![image](https://github.com/Wei-Hsi/template/blob/main/all%20project%20layout/java%20app/search%20page.png)
![image](https://github.com/Wei-Hsi/template/blob/main/all%20project%20layout/java%20app/add%20new%20word.png)
### Quiz
![image](https://github.com/Wei-Hsi/template/blob/main/all%20project%20layout/java%20app/test%20page.png)
![image](https://github.com/Wei-Hsi/template/blob/main/all%20project%20layout/java%20app/test%20result.png)
### Flashcard
![image](https://github.com/Wei-Hsi/template/blob/main/all%20project%20layout/java%20app/ENG%20flashcard.png)
![image](https://github.com/Wei-Hsi/template/blob/main/all%20project%20layout/java%20app/CHN%20flashcard.png)

***
## 05. Demo Video



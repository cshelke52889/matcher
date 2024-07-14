# matcher

program that reads a file and finds matches against a predefined set of words.

To run this file in terminal / commandline:

1. Make sure you have a file which contains the initial predefined set of words, each word on different line but in the same directory where the .java file is.
eg. PredefinedWords.txt

2. Have file which would have text to test against the predefined set of words
eg. Input.txt

3. Compile java file using:
> javac StringMatcher.java   

4. Run using
>  java StringMatcher <predefinedWords_Filename> <Input_text_filename>
eg: java StringMatcher PredefinedWords.txt Input.txt

You should see an output as below:

Predefined word           Match count

FirstName                      3500 

LastName                       2700

Zipcode                        1601 
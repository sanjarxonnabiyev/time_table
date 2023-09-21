package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiConstants;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mybot extends TelegramLongPollingBot {
    public ArrayList<String> arrayList;
    public Mybot()
    {
        arrayList=new ArrayList<>();
        arrayList.add("{Dushanba} 3-soatdan 5-soatgacha dars:\n" +
                "1) -  {    }\n" +
                "2) -  {    }\n" +
                "3) -  {11-B}\n" +
                "4) -  {11-A}\n" +
                "5) -  {5-B} \n");
        arrayList.add("{Seshanba} 2-soatdan 5-soatgacha dars:\n" +
                "1) -  {   }\n" +
                "2) -  {5-V}\n" +
                "3) -  {6-A}\n" +
                "4) -  {   }\n" +
                "5) -  {8-V}\n" +
                "6) -  {8-A}\n");
        arrayList.add("{Chorshanba} 1-soatdan 6-soatgacha dars:\n" +
                "1) -  {9-A} \n" +
                "2) -  {6-V} \n" +
                "3) -  {9-B} \n" +
                "4) -  {7-A} \n" +
                "5) -  {5-A} \n" +
                "6) -  {10} \n");
        arrayList.add("{Payshanba} 2-soatdan 6-soatgacha dars:\n" +
                "1) -  {    }" +
                "2) -  {11-A} \n" +
                "3) -  {7-B} \n" +
                "4) -  {9-B}\n" +
                "5) -  {11-B} \n" +
                "6) -  {7-V}");
        arrayList.add("{Juma} 3-soatdan 6-soatgacha dars:\n" +
                "1) -  {   } \n" +
                "2) -  {   }  \n" +
                "3) -  {6-B} \n" +
                "4) -  {8-B} \n" +
                "5) -  {10}  \n" +
                "6) -  {9-A} ");
    }

    @Override
    public void onUpdateReceived(Update update) {

        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        if (text.equals("/start")) {
            sendMessage.setText("Assalomu alekum\uD83D\uDC4C");
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            List<KeyboardRow> rowList = new ArrayList<>();
            replyKeyboardMarkup.setKeyboard(rowList);
            KeyboardRow row1 = new KeyboardRow();
            row1.add("MONDAY");
            row1.add("TUESDAY");
            row1.add("WEDNESDAY");
            rowList.add(row1);
            KeyboardRow row2 = new KeyboardRow();
            row2.add("THURSDAY");
            row2.add("FRIDAY");
            row2.add("TODAY");
            rowList.add(row2);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);

            InlineKeyboardButton menuButton = new InlineKeyboardButton();
            menuButton.setText("Go to menu");
            menuButton.setCallbackData("menu");

            InlineKeyboardButton testButton = new InlineKeyboardButton();
            testButton.setText("Go to Test");
            testButton.setCallbackData("123");

            List<InlineKeyboardButton> secondRow = new LinkedList<>();
            secondRow.add(menuButton);
            secondRow.add(testButton);

            List<List<InlineKeyboardButton>> list = new LinkedList<>();
            list.add((List<InlineKeyboardButton>) menuButton);

        }
        else if(text.toUpperCase().equals("TODAY"))
        {
            LocalDate localDate=LocalDate.now();
            DayOfWeek dayOfWeek=localDate.getDayOfWeek();
            sendMessage.setText(arrayList.get(dayOfWeek.getValue()-1));
        }
        else {
            text=text.toUpperCase();
            try {
                sendMessage.setText(arrayList.get(DayOfWeek.valueOf(text).getValue() - 1));
            }catch(IllegalArgumentException i)
            {
                sendMessage.setText("You must write the \ndays of week in english correctly e.g: Monday");
            }
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sendMessage.getChatId());
    }





    @Override
    public String getBotUsername() {
        return "t.me/uferstuz_bot";
    }

    @Override
    public String getBotToken() {
        return "6054547329:AAECwHOEbzwUr0ot5D5_B-69FDaGg-EAroE";
    }
}

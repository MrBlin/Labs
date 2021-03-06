package com.company;

import Commands.*;
import Exceptions.CmdValidationException;

import java.util.LinkedHashMap;
import java.util.Map;

public final class CmdValidator {

    private static LinkedHashMap<Integer, Movie> collection;

    public static void setMap(LinkedHashMap<Integer, Movie> films) {
        collection = films;
    }

    public static boolean validateCmd(Command inpCommand) throws CmdValidationException {
        if(inpCommand == null)
            return false;

        if (HelpCmd.class.equals(inpCommand.getClass()) || ShowCmd.class.equals(inpCommand.getClass()) ||
                InfoCmd.class.equals(inpCommand.getClass()) || ClearCmd.class.equals(inpCommand.getClass()) ||
                SaveCmd.class.equals(inpCommand.getClass()) || HistoryCmd.class.equals(inpCommand.getClass()) ||
                MinByUsaBoxOfficeCmd.class.equals(inpCommand.getClass()) || ExitCmd.class.equals(inpCommand.getClass()) ||
                ExecuteScriptCmd.class.equals(inpCommand.getClass()) || RemoveGreaterKeyCmd.class.equals(inpCommand.getClass()) ||
                RemoveLowerKeyCmd.class.equals(inpCommand.getClass()) || CountGreaterThanUsaBoxOfficeCmd.class.equals(inpCommand.getClass()) ||
                CountByUsaBoxOfficeCmd.class.equals(inpCommand.getClass())) {
            return true;

        } else if (InsertCmd.class.equals(inpCommand.getClass())) {
            InsertCmd insertCmd = (InsertCmd) inpCommand;
            if (collection.get(insertCmd.getK()) == null)
                return true;
            else
                throw new CmdValidationException("элемент с таким ключом уже существует. Воспользуйтесь командой " +
                        "/update или введите неиспользуемый ключ");

        } else if (UpdateCmd.class.equals(inpCommand.getClass())) {
            UpdateCmd updateCmd = (UpdateCmd) inpCommand;
            int id = updateCmd.getId();
            for (Map.Entry<Integer, Movie> entry : collection.entrySet())
                if (entry.getValue().getId() == id)
                    return true;
            throw new CmdValidationException("элемента с таким id не существует");

        } else if (RemoveKeyCmd.class.equals(inpCommand.getClass())) {
            RemoveKeyCmd removeKeyCmd = (RemoveKeyCmd) inpCommand;
            int K = removeKeyCmd.getK();
            if (collection.get(K) != null)
                return true;
            else
                throw new CmdValidationException("элемента с таким ключом не существует");

        } else if (ReplaceIfGreaterCmd.class.equals(inpCommand.getClass())) {
            ReplaceIfGreaterCmd replaceIfGreaterCmd = (ReplaceIfGreaterCmd) inpCommand;
            int K = replaceIfGreaterCmd.getK();
            if (collection.get(K) != null) {
                return true;
            } else
                throw new CmdValidationException("элемента с таким ключом не существует");
        }
        return false;
    }
}
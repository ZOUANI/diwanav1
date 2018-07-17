/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.StringUtil;

/**
 *
 * @author YOUNES
 */
public class BonSortieMessageManager {

    private static Map<String, String> errorMessages = new HashMap();
    private static List<String> errors = new ArrayList();
    private static String xmlFileNameNotValidMessage = "Le nom du fichier xml doit etre sous forme BS_AAAAMMJJHHMMSS_referenceDUM.xml ou ACK_BS_AAAAMMJJHHMMSS_referenceDUM.xml";
    private static String xmlFileNameAndFileContentNotValidMessage = "Le contenu de la date et de la reference dum du fichier xsl doit etre conforme avec le nom du fichier xml";
    private static String typeActionNotValidMessage = "Merci de choisir soit Create, Edit ou Remove";

    static {
        errorMessages.put("typeDs", "Type Ds Obligatoire si la référence de la DS  est non servie et les valeurs possibles sont 01, 03, 04, 05, 06 et 07");
        errorMessages.put("lieuChargement", "Lieu Chargement Obligatoire si la référence de la DS  est servie et les valeurs possibles sont UN et LOCODE");
        errorMessages.put("referenceLotDedouanement", "Reference Lot Dedouanement Obligatoire si la référence de la DS  est servie");
        errorMessages.put("typeContenant", "Type Contenant Obligatoire si la référence de la DS  est servie");
        errorMessages.put("nombreContenant", "Nombre Contenant Obligatoire sauf si le code de type Contenant appartient à {000, 001, 010, 020, 030, 040, 050, 060}");
        errorMessages.put("marqueMarchandise", "Marque Marchandise Obligatoire sauf si la référence de la DS est non fournie");
        errorMessages.put("poidsNet", " Poids Net Obligatoire si : \n"
                + "- Le nombre de servi, OU la MLV est délivrée avec pesage, \n"
                + "ET \n"
                + "- La tare et le poids brut sont non servis ");
        errorMessages.put("tare", " Tare Obligatoire si : \n"
                + "- Le nombre de servi, OU la MLV est délivrée avec pesage, \n"
                + "ET \n"
                + "- Le poids net est non servis ");
        errorMessages.put("poidsBrut", "Poids Brut Obligatoire si : \n"
                + "- Le nombre de servi, OU la MLV est délivrée avec pesage, \n"
                + "ET \n"
                + "- Le poids net est non servis ");
        errorMessages.put("referenceBonSortie", " Référence du bon de sortie Obligatoire si Code = OK");
    }

    public static String getErrorMessageTypeDs(int res) {
        return getErrorMessage(res, "typeDs");
    }

    public static String getErrorMessageLieuChargement(int res) {
        return getErrorMessage(res, "lieuChargement");
    }

    public static String getErrorMessageReferenceLotDedouanement(int res) {
        return getErrorMessage(res, "referenceLotDedouanement");
    }

    public static String getErrorMessageTypeContenant(int res) {
        return getErrorMessage(res, "typeContenant");
    }

    public static String getErrorMessageNombreContenant(int res) {
        return getErrorMessage(res, "nombreContenant");
    }

    public static String getErrorMessageMarqueMarchandise(int res) {
        return getErrorMessage(res, "marqueMarchandise");
    }

    public static String getErrorMessagePoidsNet(int res) {
        return getErrorMessage(res, "poidsNet");
    }

    public static String getErrorMessageTare(int res) {
        return getErrorMessage(res, "tare");
    }

    public static String getErrorMessagePoidsBrut(int res) {
        return getErrorMessage(res, "poidsBrut");
    }

    static String getErrorMessageReferenceBonSortie(int res) {
        return getErrorMessage(res, "referenceBonSortie");
    }

    private static String getErrorMessage(int res, String code) {
        if (res < 0) {
            errors.add(errorMessages.get(code));
        }
        return null;
    }

    public static String getErrors() {
        return StringUtil.transformListToString(errors);
    }

    public static void clearMessages() {
        errors.clear();
    }

    public static String getXmlFileNameNotValidMessage() {
        return xmlFileNameNotValidMessage;
    }

    public static String getXmlFileNameAndFileContentNotValidMessage() {
        return xmlFileNameAndFileContentNotValidMessage;
    }

    public static String getTypeActionNotValidMessage() {
        return typeActionNotValidMessage;
    }

}

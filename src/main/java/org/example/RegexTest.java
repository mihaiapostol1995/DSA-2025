package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexTest {

    private static String toDate = "to_date\\('([^']*)'\\s*,\\s*'DD-MON-RR'\\)";

    public static void main(String[] args) throws Exception {
        String s = "{\"apiVersion\":\"apps/v1\",\"kind\":\"Deployment\",\"metadata\":{\"annotations\":{},\"name\":\"ipa-logger\",\"namespace\":\"ipa-ha-tst\"},\"spec\":{\"replicas\":0,\"selector\":{\"matchLabels\":{\"app\":\"ipa-logger\"}},\"template\":{\"metadata\":{\"labels\":{\"app\":\"ipa-logger\"}},\"spec\":{\"containers\":[{\"image\":\"image-registry.openshift-image-registry.svc:5000/ipa-ha-tst/ipalogger@sha256:d6ea7b166cac55dfe72f8afcb68c4ab4acf21a066f89f6df13f5ec790ea523cc\",\"name\":\"ipa-logger\"}]}}}}";
        Pattern r = Pattern.compile("\"image\":\"([^\"]+)\"");
        Matcher m = r.matcher(s);

        m.find();

        String s1 = replaceDateFunction("Insert into HEDGE_COMPONENT (ID,HEDGE_ID,TYPE,TRADE_ID,TRADE_TYPE,PROPORTION,RATE,CREDIT_SPREAD,CCY,NOTIONAL,TRADE_VERSION,EFFECTIVE_DATE,END_DATE,COUNTERPARTY_ID,BOOK,VAR_RATE_INDEX,VAR_RATE_DAY_CONV,MTM_T0,PAYER_OR_RECEIVER,VAR_RATE_FREQUENCY,ADJUSTED_NOTIONAL,SOURCE_SYSTEM,RATE_GUID,RETRY_COUNT,TRACE_DETAILS,HEDGE_BALANCE,NPV,TRADE_STATUS,CUSTOMER) values (34019,to_date('506881MH','DD-MON-RR'),to_date('HEDGING','DD-MON-RR'),to_date('38423880I1','DD-MON-RR'),to_date('SWAP','DD-MON-RR'),100,0.02851,null,to_date('EUR','DD-MON-RR'),100000000,4,to_date('16-NOV-22','DD-MON-RR'),to_date('16-NOV-31','DD-MON-RR'),to_date('36004367','DD-MON-RR'),to_date('1709','DD-MON-RR'),to_date('FXE6M','DD-MON-RR'),to_date('A360','DD-MON-RR'),null,null,to_date('6M','DD-MON-RR'),null,to_date('MUREX','DD-MON-RR'),null,null, EMPTY_CLOB(),null,null,'VER','CUSTOMER');");
        String s2 = "Mihai";

        var is = loadInputStream("inserts.txt");
        String s3 = replaceDateFunction(new String(is.readAllBytes()));
        Files.writeString(Path.of("output.txt"), s3);
    }

    public static InputStream loadInputStream(String fileName) {
        return RegexTest.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static String replaceDateFunction(String input) {
        // Pattern to match to_date('VALUE','DD-MON-RR')
        // The pattern captures the value inside the first pair of quotes
        Pattern pattern = Pattern.compile("to_date\\('([^']*)'\\s*,\\s*'DD-MON-RR'\\)");
        Matcher matcher = pattern.matcher(input);

        StringBuffer sb = new StringBuffer();

        // Replace each occurrence with just the quoted value
        while (matcher.find()) {
            String dateValue = matcher.group(1); // Extract the captured value
            matcher.appendReplacement(sb, "'" + dateValue + "'");
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}

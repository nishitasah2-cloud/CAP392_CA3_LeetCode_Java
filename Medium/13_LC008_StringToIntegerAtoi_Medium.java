class InvalidInputException extends Exception{
    InvalidInputException(String message){
        super(message);
    }
}

class Solution{

    public int myAtoi(String s){

        int sign = 1;
        int i = 0;
        long result = 0;
        try{
            if (s == null){
                throw new InvalidInputException("Invalid input");
            }
            // Ignore leading spaces
            while (i < s.length() && s.charAt(i) == ' '){
                i++;
            }
            // Check sign
            if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')){
                if (s.charAt(i) == '-'){
                    sign = -1;
                }
                i++;
            }
            // At least one digit is required
            if (i >= s.length() || !Character.isDigit(s.charAt(i))){
                throw new InvalidInputException("No digits found");
            }

            long limit = (sign == 1) ? Integer.MAX_VALUE : 2147483648L;

            while (i < s.length() && Character.isDigit(s.charAt(i))){
                int digit = s.charAt(i) - '0';

                // Check overflow before updating result
                if (result > (limit - digit) / 10){
                    throw new NumberFormatException("Integer overflow");
                }
                result = result * 10 + digit;
                i++;
            }

            return (int)(result * sign);

        } catch(NumberFormatException e){
            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        } catch(InvalidInputException e){
            return 0;

        } finally{
            // Finally block executes in every case.
        }
    }
}
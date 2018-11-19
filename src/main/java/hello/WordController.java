package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {
    // TODO Implement the /words/{word} endpoint
    
    @GetMapping("/words/{word}")
    String palindromeCheck(@PathVariable String word){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("word",word);
        objectNode.put("palindrome",checkPalindrome(word));
        objectNode.put("anagramOfPalindrome",checkAnagramOfPalindrome(word));
        return objectNode.toString();
    }

    /**
     *
     * @param input
     * @return
     */
    public static boolean checkAnagramOfPalindrome(String input)
    {
        int [] count = new int[26];
        for( int i = 0; i < input.length(); i++ )
        {
            char ch = input.charAt(i);
            count[ch-'a']++;
        }
        int oddOccur = 0;
        for( int cnt:count )
        {
            if( oddOccur > 1)
                return false;
            if( cnt%2 == 1 )
                oddOccur++;
        }
        return true;
    }

    /**
     *
     * @param input
     * @return
     */
    public static boolean checkPalindrome(String input){
        String rev = "";
        int length = input.length();
        for ( int i = length - 1; i >= 0; i-- )
            rev = rev + input.charAt(i);
        if (input.equals(rev))
            return true;
        return false;
    }
}

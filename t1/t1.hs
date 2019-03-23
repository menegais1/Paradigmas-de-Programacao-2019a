import Data.Char

isVowel :: Char -> Bool
isVowel c = ((toLower c) == 'a' || (toLower c)=='e' ||(toLower c) == 'i' || (toLower c)=='o' || (toLower c)=='u') 

addComma :: [String] -> [String]
addComma l = map (++ ",") l

htmlListItemsA :: [String] -> [String]
htmlListItemsA x = map (\n -> "<LI>" ++ n ++ "</LI>") x

addLI :: String -> String
addLI x =  "<LI>" ++ x ++ "</LI>"

htmlListItemsB :: [String] -> [String]
htmlListItemsB x = map addLI x

removeVowelA :: String -> String
removeVowelA x = filter (\n -> isVowel (toLower n) /= True) x

removeVowelB :: String -> String
removeVowelB x = filter (\c -> ((toLower c) == 'a' || (toLower c)=='e' ||(toLower c) == 'i' || (toLower c)=='o' || (toLower c)=='u') /= True) x

returnTraco :: Char -> Char
returnTraco n = if n /= ' ' then '-' else n

removeNotSpaceA :: String -> String
removeNotSpaceA x = map returnTraco x

removeNotSpaceB :: String -> String
removeNotSpaceB x = map (\n -> if n /= ' ' then '-' else n) x

firstName :: String -> String
firstName x = takeWhile (/= ' ') x

isDigitA :: Char -> Bool
isDigitA x = x >= '0' && x <= '9'

isInt :: String -> Bool
isInt x = length (filter (\n -> not (isDigitA n)) x) == 0

lastName :: String -> String
lastName x = reverse (firstName (reverse x))

userName :: String -> String
userName x = [toLower (head x)] ++ (map (toLower) (lastName x))

returnCharInt :: Char -> Char
returnCharInt c
                | c == 'a' = '4'
                | c == 'e' = '3'
                | c == 'i' = '2'
                | c == 'o' = '1'
                | c == 'u' = '0'
                | otherwise = c


returnCharInt2 :: Char -> String
returnCharInt2 c 
                |  c == 'a' = "4"
                |  c == 'e' = "3"
                |  c == 'i' = "1"
                |  c == 'o' = "0"
                |  c == 'u' = "00"
                | otherwise = [c]

encodeName :: String -> String
encodeName x = map (\n -> returnCharInt (toLower n)) x

betterEncodeName :: String -> String
betterEncodeName x = concatMap (\n -> returnCharInt2 (toLower n)) x

take10 :: String -> String
take10 n = take 10 n

addPoint :: String -> String
addPoint n = take10 (n ++ "..........")

abbreviate :: [String] -> [String]
abbreviate list = map (\str -> if (length str) > 10 then (take10 str) else (addPoint str)) list

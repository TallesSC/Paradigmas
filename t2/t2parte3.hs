import Data.Char

stringToIntList :: String -> [Int]
stringToIntList "" = []
stringToIntList string = digitToInt (head string) : stringToIntList (tail string)

multiplicador :: [Int] -> [Int]
multiplicador digits = zipWith (*) digits [1,3,1,3,1,3,1,3,1,3,1,3,1] 

somador :: [Int] -> Int
somador [] = 0
somador list = head list + somador (tail list)

checkDigit :: Int -> Int
checkDigit soma = 10 - (soma `mod` 10)

isEanOk :: String -> Bool
isEanOk string
  | length string /= 13 = False
  | otherwise = if digitToInt (last string) == checkDigit(somador(multiplicador(stringToIntList (init string)))) then True else False

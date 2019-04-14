import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

pinkPalette :: Int -> [(Int,Int,Int)]
pinkPalette n = [(140+i*5, 31 , 183) | i <- [0..n] ]

svgBegin :: Float -> Float -> String
svgBegin w h = printf "<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h 


svgEnd :: String
svgEnd = "</svg>"

-- Gera string com atributos de estilo para uma dada cor
-- Atributo mix-blend-mode permite misturar cores
svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b


svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles

svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style

svgCircle :: Circle -> String -> String 
svgCircle ((x,y),r) style = 
  printf "<circle cx='%.3f' cy='%.3f' r='%.3f' style='%s' />" x y r style
  

genRects :: Float -> Float -> Int -> Int -> Int -> [Rect]
genRects w h l c gap = 
  [(((fromIntegral gap + w) *  (fromIntegral i), (fromIntegral gap + h) * (fromIntegral j)),w,h) | i <- [0..l - 1], j <- [0..c - 1]]

genGridPosition :: Float -> Float -> Int -> Int -> Int -> [Point]
genGridPosition w h l c gap = 
  [(((squareWidth / 2 + fromIntegral gap) * fromIntegral j), ((squareHeigth / 2 + fromIntegral gap) * fromIntegral i)) | i <- [1..l], j <- [1..c]]
  where squareWidth = w / fromIntegral c
        squareHeigth = h / fromIntegral l

genCircles :: Point -> Float -> Float -> Int -> Float -> [Circle]
genCircles (x,y) r totalR nCircles lastValue = 
  [((x + (cos i * totalR),y - (sin i * totalR)),r) | i <- [0,step..lastValue]]
  where step = lastValue / fromIntegral (nCircles - 1)


genCirclesSenoid :: Point -> Float -> Int -> Float -> Float -> (Point) -> [Circle]
genCirclesSenoid (x,y) r nCircles lastValue gap (desX, desY) = 
  [((i * gap + desX, sin i * gap  + desY),r) | i <- [0,step..lastValue - step]]
  where step = lastValue / fromIntegral nCircles
        

genCase1 :: IO()
genCase1 = do
  writeFile "case1.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = genRects 30 30 10 10 12
        palette = pinkPalette nrects
        nrects = 100
        (w,h) = (1500,500) -- width,height da imagem SVG

genCase2 :: IO()
genCase2 = do
  writeFile "case2.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles = genCircles (w/2,h/2) 15 100 15 pi2
        pi2 = pi * 2
        palette = rgbPalette ncircles
        ncircles = 16
        (w,h) = (1500,500) -- width,height da imagem SVG

genCase3 :: IO()
genCase3 = do
  writeFile "case3.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles =  concat [genCircles (x,y) 25 18 3 pi | (x,y) <- genGridPosition 500 500 3 3 30]
        palette = rgbPalette ncircles
        ncircles = 27
        (w,h) = (1500,500) -- width,height da imagem SVG

genCase4 :: IO()
genCase4 = do
  writeFile "case4.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circles (map svgStyle palette)
        circles =  concat [genCirclesSenoid (0,0) 10 20 pi2 50 (50,desY) | desY <- [spaceLinhas,spaceLinhas * 2..nLinhas*spaceLinhas]]
        nLinhas = 4
        spaceLinhas = 100
        pi2 = pi * 2
        palette = rgbPalette ncircles
        ncircles = 80
        (w,h) = (1500,500) -- width,height da imagem SVG

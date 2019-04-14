import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

pinkPalette :: Int -> [(Int,Int,Int)]
pinkPalette n = [(140+i*5, 31 , 183) | i <- [0..n] ]

genRects :: Float -> Float -> Int -> Int -> Int -> [Rect]
genRects w h l c gap = [(((fromIntegral gap + w) *  (fromIntegral i), (fromIntegral gap + h) * (fromIntegral j)),w,h) | i <- [0..l - 1], j <- [0..c - 1]]


svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style


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


genCase1 :: IO()
genCase1 = do
  writeFile "case1.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = genRects 30 30 10 10 12
        palette = pinkPalette nrects
        nrects = 100
        (w,h) = (1500,500) -- width,height da imagem SVG

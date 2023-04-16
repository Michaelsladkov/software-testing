function [y] = onPos(x)
    y = ((log10(x)*log2(x)-log2(x)-log10(x*x))/log2(x))*log10(x)
endfunction

function [y] = onNeg(x)
    y = (tan(x)-cos(x))*sec(x)/cotg(x)+sec(x)/tan(x)+((sec(x)+cos(x))*((tan(x)/(cos(x)*sin(x)))/tan(x)))
endfunction

function [y] = tf(x)
    if x <0 then
        y = onNeg(x)
    else
        y = onPos(x)
    end
endfunction

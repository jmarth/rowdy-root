open FILE, "product.txt" or die "Can't open file: $!";
open OUT, ">", "out.txt" or die "Cant open file: $!";

while ($line = <FILE>) {
	@pieces = split /\t/, $line;
	
	print OUT "$pieces[3]:$pieces[5]\n";	
}
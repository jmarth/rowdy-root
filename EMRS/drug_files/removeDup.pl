open FILE, "out.txt" or die "Can't open file: $!";
open OUT, ">", "outNoDup.txt" or die "Cant open file: $!";

$prev = "";

while ($line = <FILE>) {
	@pieces = split /:/, $line;
	if ($prev) {
		$curr = $pieces[0];
		if (lc $prev eq  lc $curr) {
			next;
		}
		else {
			$prev = $pieces[0];
			print OUT "$pieces[0]:$pieces[1]";
		}
	}
	else {
		$prev = $pieces[0];
		print OUT "$pieces[0]:$pieces[1]";	
	}
}
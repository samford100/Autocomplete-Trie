require 'open-uri'


website = "https://en.wikipedia.org/wiki/List_of_terms_relating_to_algorithms_and_data_structures"

out_file = File.new("Algorithms and Data Structures.txt", "w")

web_file = open(website)
contents = web_file.read
# puts contents

# <li><a href="/wiki/Adaptive_sort" title="Adaptive sort">adaptive sort</a></li>

title_start = 13123
title_end = 13123
# contents.index(" title=\"", title_start)
while title_start < 164550
	title_start = contents.index(" title=\"", title_start) + 8
	title_end = contents.index("\"", title_start + 3) - 1
	if contents[title_start..title_start + 3] != "Edit"
	# puts title_start
	# puts title_end
		puts contents[title_start..title_end]
		out_file.write(contents[title_start..title_end] + "\n")
	end
end



# while head_start != nil
# 	#out_file.puts contents[head_start..head_end]
# 	puts contents[head_start..head_end]
# 	head_start = contents.count("<h1>", head_end + 3)
# 	head_end = contents.count("</h1>", head_start + 2)
# end
out_file.close



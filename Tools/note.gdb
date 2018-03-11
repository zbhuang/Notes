1. Print the elements of a C++ vector in GDB

Reference
1) How do I print the elements of a C++ vector in GDB?
https://stackoverflow.com/questions/253099/how-do-i-print-the-elements-of-a-c-vector-in-gdb

2) How to view contents of STL containers using GDB 7.x
https://stackoverflow.com/questions/2492020/how-to-view-contents-of-stl-containers-using-gdb-7-x/2492341#2492341
Get the python viewers from SVN

svn://gcc.gnu.org/svn/gcc/trunk/libstdc++-v3/python
Add the following to your ~/.gdbinit

python
import sys
sys.path.insert(0, '/path/to/pretty-printers/dir')
from libstdcxx.v6.printers import register_libstdcxx_printers
register_libstdcxx_printers (None)
end
Then print should just work:

std::map<int, std::string> the_map;
the_map[23] = "hello";
the_map[1024] = "world";
In gdb:

(gdb) print the_map 
$1 = std::map with 2 elements = { [23] = "hello", [1024] = "world" }
To get back to the old view use print /r (/r is for raw).

3) STL Support Tools
http://sourceware.org/gdb/wiki/STLSupport

4) Professional Assembly Language
https://github.com/shihyu/Assembly/find/master
https://github.com/shihyu/Assembly/tree/master/books


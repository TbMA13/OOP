cmake_minimum_required(VERSION 3.26)
project(diff)

set(CMAKE_CXX_STANDARD 17)
include_directories(inc)
add_executable(diff main.cpp
        inc/diff.h)

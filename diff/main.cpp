#include <iostream>
#include <diff.h>
#include <vector>
#include <windows.h>

double f(double x){
    return x * x;
}

int main() {
    SetConsoleCP(1251);
    SetConsoleOutputCP(1251);
    auto* left = new leftDiff();
    auto* middle = new middleDiff();
    auto* right = new rightDiff();

    double x0 = 5;
    std::vector<double> dotesArray = {10, 11, 12};

    std::cout << left->calc(f, x0) << " - левая производная" << std::endl;
//    std::cout << left->calc(f, x0, 0.00000000001) << std::endl;
//    std::cout << left->calc(f, x0, dotesArray) << std::endl;

    std::cout << middle->calc(f, x0) << " - средняя производная" << std::endl;

    std::cout << right->calc(f, x0) << " - правая производная" << std::endl;


    return 0;
}

#include <iostream>
#include <diff.h>
#include <vector>
#include <windows.h>

double f(double x) {
    return x * x;
}

int main() {
    SetConsoleCP(1251);
    SetConsoleOutputCP(1251);

    auto *left = new leftDiff();
    auto *middle = new middleDiff();
    auto *right = new rightDiff();

    double x0 = 10;
    std::vector<double> dotesArray = {10, 10.2, 10.5, 10.7, 11};
    dotesArray = {0};
    std::cout << left->calc(f, x0) << " - левая производная; " << left->accuracy() << " - точность" << std::endl;

    try {
        std::cout << left->calc(f, x0, dotesArray) << std::endl;
    }
    catch (const std::exception &e) {
        std::cout << e.what() << std::endl;
    }

    try {
        std::cout << left->calc(f, x0, 0) << std::endl;
    }
    catch (const std::exception &e) {
        std::cout << e.what() << std::endl;
    }

    std::cout << middle->calc(f, x0) << " - средняя производная; " << middle->accuracy() << " - точность" << std::endl;;

    std::cout << right->calc(f, x0) << " - правая производная; " << right->accuracy() << " - точность" << std::endl;

    delete left;
    delete right;
    delete middle;

    return 0;
}

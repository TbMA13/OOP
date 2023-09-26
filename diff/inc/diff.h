#pragma once

#include <functional>
#include <vector>
#include <exception>

class diff {
protected:
    double X0{};
    double Dx{};
    double Accuracy{0};

    virtual double dyDxCalc(const std::function<double(double)> &f) { return 0; };
public:
    [[nodiscard]] double accuracy() const {
        return Accuracy;
    }

    double calc(const std::function<double(double)> &f, double x0) {
        Dx = 0.0001;
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, const std::vector<double> &dotes) {
        if (dotes.size() < 2){
            throw std::exception("В массиве с координатами должно быть хотя бы две различных значения!");
        }
        bool redFlag = false;
        for (int i = 0; i < dotes.size() - 1; i++){
            for (int j = i + 1; j < dotes.size(); j++){
                if (dotes[i] != dotes[j]){
                    redFlag = true;
                }
                if (redFlag){
                    break;
                }
            }
            if (redFlag){
                break;
            }
        }
        if (!redFlag){
            throw std::exception("В массиве с координатами должно быть хотя бы две различных значения!");
        }
        Dx = (*max_element(dotes.begin(), dotes.end()) - dotes[0]) / (static_cast<double>(dotes.size()) - 1);
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, double step) {
        if (step == 0){
            throw std::exception("Приращение не может быть равным нулю");
        }
        Dx = step;
        X0 = x0;
        return dyDxCalc(f);
    }
};

class leftDiff : public diff {
protected:
    double dyDxCalc(const std::function<double(double)> &f) override {
        Accuracy = Dx;
        return static_cast<double>(f(X0) - f(X0 - Dx)) / Dx;
    }
};

class rightDiff : public diff {
protected:
    double dyDxCalc(const std::function<double(double)> &f) override {
        Accuracy = Dx;
        return static_cast<double>(f(X0 + Dx) - f(X0)) / Dx;
    }
};

class middleDiff : public diff {
protected:
    double dyDxCalc(const std::function<double(double)> &f) override {
        Accuracy = Dx * Dx;
        return static_cast<double>(f(X0 + Dx) - f(X0 - Dx)) / (2 * Dx);
    }
};
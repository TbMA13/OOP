#pragma once

#include <functional>
#include <vector>

class diff {
protected:
    double X0{};
    double Dx{};

    virtual double dyDxCalc(const std::function<double(double)> &f) { return 0; };

public:

    virtual double calc(const std::function<double(double)> &f, double x0) {
        return 0;
    }

    virtual double calc(const std::function<double(double)> &f, double x0, const std::vector<double> &dotes) {

        return 0;
    }

    virtual double calc(const std::function<double(double)> &f, double x0, double step) {
        return 0;
    }
};

class leftDiff : public diff {
private:
    double dyDxCalc(const std::function<double(double)> &f) override {
        return static_cast<double>(f(X0) - f(X0 - Dx)) / Dx;
    }

public:
    double calc(const std::function<double(double)> &f, double x0) override {
        Dx = 0.00000000001;
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, const std::vector<double> &dotes) override {
        Dx = (dotes[dotes.size() - 1] - dotes[0]) / (static_cast<double>(dotes.size()) - 1);
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, double step) override {
        Dx = step;
        X0 = x0;
        return dyDxCalc(f);
    }
};

class rightDiff : public diff {
private:
    double dyDxCalc(const std::function<double(double)> &f) override {
        return static_cast<double>(f(X0 + Dx) - f(X0)) / Dx;
    }

public:
    double calc(const std::function<double(double)> &f, double x0) override {
        Dx = 0.00000000001;
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, const std::vector<double> &dotes) override {
        Dx = (dotes[dotes.size() - 1] - dotes[0]) / (static_cast<double>(dotes.size()) - 1);
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, double step) override {
        Dx = step;
        X0 = x0;
        return dyDxCalc(f);
    }
};

class middleDiff : public diff {
private:
    double dyDxCalc(const std::function<double(double)> &f) override {
        return static_cast<double>(f(X0 + Dx) - f(X0 - Dx)) / (2 * Dx);
    }

public:
    double calc(const std::function<double(double)> &f, double x0) override {
        Dx = 0.00000000001;
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, const std::vector<double> &dotes) override {
        Dx = (dotes[dotes.size() - 1] - dotes[0]) / (static_cast<double>(dotes.size()) - 1);
        X0 = x0;
        return dyDxCalc(f);
    }

    double calc(const std::function<double(double)> &f, double x0, double step) override {
        Dx = step;
        X0 = x0;
        return dyDxCalc(f);
    }
};
package com.example.roll_dice

import java.util.Stack

object ExpressionEvaluation {
    fun evaluate(expression: String): Int {
        val operands = Stack<Int>()
        val operators = Stack<Char>()
        var i = 0
        while (i < expression.length) {
            val ch = expression[i]
            if (ch == ' ') {
                i++
                continue
            }
            if (Character.isDigit(ch)) {
                var num = 0
                while (i < expression.length && Character.isDigit(expression[i])) {
                    num = num * 10 + Character.getNumericValue(expression[i])
                    i++
                }
                i--
                operands.push(num)
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.empty() && hasPrecedence(ch, operators.peek())) {
                    operands.push(applyOperation(operators.pop(), operands.pop(), operands.pop()))
                }
                operators.push(ch)
            }
            i++
        }
        while (!operators.empty()) {
            operands.push(applyOperation(operators.pop(), operands.pop(), operands.pop()))
        }
        return operands.pop()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
    }

    private fun applyOperation(op: Char, b: Int, a: Int): Int {
        when (op) {
            '+' -> return a + b
            '-' -> return a - b
            '*' -> return a * b
            '/' -> {
                if (b == 0) {
                    throw UnsupportedOperationException("Cannot divide by zero")
                }
                return a / b
            }
        }
        return 0
    }
}
#!/bin/sh

pdftoppm -png app/static/tickets/$1 > app/static/tickets/$2

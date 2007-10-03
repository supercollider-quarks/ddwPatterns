
// misc pattern extensions

+ Pattern {
	if { |truepattern, falsepattern, default = 0|
		^Pif(this, truepattern, falsepattern, default)
	}
	
	oneValuePerBeat { |minDelta = 0|
		^Penvir((delta: Pdiff(Ptime(inf)).asStream), StreamClutch(this, { ~delta.next > minDelta }), false)
	}
}


+ Pwhite {
	embedInStream { arg inval;
		var	loStream = lo.asStream,
			hiStream = hi.asStream;
		length.do({
			inval = rrand(loStream.next(inval) ?? { ^inval },
				hiStream.next(inval) ?? { ^inval })
				.yield;
		});
		^inval;
	}
}

+ Pseries {
	*fromEndpoints { |start, end, length = 2|
		(length >= 2 and: { length != inf }).if({
			length = length.asInteger;
			^this.new(start, (end - start) / (length - 1), length)
		}, {
			Error("Pseries:fromEndpoints - length must be finite and >= 2, received %."
				.format(length)).throw;
		});
	}
}

+ Pgeom {
	*fromEndpoints { |start, end, length = 2|
		(length >= 2 and: { length != inf }).if({
			length = length.asInteger;
			^this.new(start, pow(end / start, (length - 1).reciprocal), length)
		}, {
			Error("Pgeom:fromEndpoints - length must be finite and >= 2, received %."
				.format(length)).throw;
		});
	}
}


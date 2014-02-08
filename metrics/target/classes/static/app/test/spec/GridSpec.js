describe("GridListConverter", function() {
	var gridConverter;

	beforeEach(function() {
		gridConverter = new GridConverter();
	});

	it("should calculate rigth rows size", function() {
		var data = [ 1, 2, 3, 4, 5, 6, 7 ];

		var size = gridConverter.calculateRowsSize(data, 3)
		expect(size).toEqual(3);

	});
	
	it("should split data for 3 col grid", function() {
		var data = [ 1, 2, 3, 4, 5, 6, 7 ];

		var result = gridConverter.convert(data, 3)
		
		expect(result.length).toEqual(3);
	});
	
	it("should split data for 3 col grid with exacly data", function() {
		var data = [ 1, 2, 3, 4, 5, 6, 7 ];

		var result = gridConverter.convert(data, 3)
		
		expect(result[0]).toContain(1,2,3);
		expect(result[1]).toContain(4,5,6);
		expect(result[2]).toContain(7);
	});
	

	it("should split data 2 col grid", function() {
		var data = [ 1, 2, 3, 4, 5, 6, 7 ];

		var result = gridConverter.convert(data, 2)
		
		expect(result[0]).toContain(1,2);
		expect(result[1]).toContain(3,4);
		expect(result[2]).toContain(5,6);
		expect(result[3]).toContain(7);
	});

});
